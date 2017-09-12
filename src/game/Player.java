package game;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


import general.Bounding;
import general.Config;
import general.KeyHandler;
import general.Message;
import map.Cube;
import map.Enemy;
import map.Lvl;
import map.Sweg;

class Player {

    private boolean onTop, onBot, onRightSide, onLeftSide;

    private boolean isLookingRight = true;

    private boolean scrollingRight, scrollingLeft;
    private boolean running = true;

    private boolean alive = true;

    private int timeSinceJump;
    private boolean jumping = false;

    private int swegCollected = 0;
    private int kills = 0;
    private int lifes = 3;

    private boolean showEscDialog = false;

    private int timeDead = 0;

    private int escapingTime = 0;

    private final Bounding bounding;
    private final Bounding botBounding;

    private BufferedImage lookingLeft, lookingRight, lookDead;
    private NextAction nextAction = null;

    private Message message;
    private KeyHandler keyHandler;

    private final Lvl lvl;

    Player(Lvl lvl, KeyHandler keyHandler) {

        double startX = 300;
        double startY = 300;

        createLook();

        bounding = new Bounding(startX, startY, lookingLeft.getWidth(), lookingLeft.getHeight());
        botBounding = new Bounding(startX, startY + 20, lookingLeft.getWidth(), 20);

        this.lvl = lvl;
        this.keyHandler = keyHandler;

        createMessage("nao i need to find teh bikmek");
    }


    void update(double delta) {


        lvl.update(scrollingLeft, scrollingRight, delta);


        checkSweg();
        checkCubeCollisions();
        checkEnemies();
        checkBigMek();


        scroll(delta);

        respawn(keyHandler);
        move(keyHandler, delta);
        jump(keyHandler, delta);

        if (checkIfEscape(keyHandler)) {
            nextAction = NextAction.EXIT;
            running = false;
        }

        if (checkLvlUp(keyHandler)) {
            running = false;
            nextAction = NextAction.LVLUP;
        }
    }


    boolean getRunning() {
        return running;
    }

    private boolean checkIfEscape(KeyHandler keyHandler) {
        if (showEscDialog) {
            escapingTime += Config.msPerFrame;
            if (keyHandler.getEnter()) return true;
            if (escapingTime > Config.escTime) {
                escapingTime = 0;
                showEscDialog = false;
            }

        }
        if (keyHandler.getEscape()) {
            showEscDialog = true;
            createMessage("press enter to get out and fak awf");
        }
        return false;
    }

    private void createLook() {

        try {
            lookingRight = ImageIO.read(getClass().getClassLoader().getResourceAsStream("img/spoder_right.png"));
            lookingLeft = ImageIO.read(getClass().getClassLoader().getResourceAsStream("img/spoder_left.png"));
            lookDead = ImageIO.read(getClass().getClassLoader().getResourceAsStream("img/blood.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    Rectangle2D.Double getBounding() {
        return bounding;
    }

    private void respawn(KeyHandler keyHandler) {
        if (alive)
            return;
        if (lifes < 0)
            return;

        timeDead += Config.msPerFrame;

        if (keyHandler.getSpace() && timeDead > Config.playerRespawnTime) {
            alive = true;

            createMessage("respawn  now am angri as fuk");
            timeDead = 0;
        }
    }

    private void checkCubeCollisions() {
        onRightSide = false;
        onLeftSide = false;
        onBot = false;
        onTop = false;
        for (Cube cube : lvl.getCubes()) {
            if (bounding.intersects(cube.getLeftBounding()))
                onLeftSide = true;
            if (bounding.intersects(cube.getRightBounding()))
                onRightSide = true;
            if (botBounding.intersects(cube.getTopBounding()))
                onTop = true;
            if (bounding.intersects(cube.getBotBounding()))
                onBot = true;
        }

    }

    private void move(KeyHandler keyHandler, double delta) {


        if (!alive) return;

        if (!onTop)
            bounding.y += Config.gravity*delta;

        if (keyHandler.getLeft() && !onRightSide && !scrollingLeft) {
            bounding.x -= Config.playerMoveSpeed*delta;
            isLookingRight = false;
        }

        if (keyHandler.getRight() && !onLeftSide && !scrollingRight) {
            bounding.x += Config.playerMoveSpeed*delta;
            isLookingRight = true;

        }

        botBounding.x = bounding.x;
        botBounding.y = bounding.y+20;

    }

    private void jump(KeyHandler keyHandler, double delta) {
        if (!alive)
            return;
        //start new jump
        if (onTop && !onBot && keyHandler.getSpace())
            jumping = true;

        //actual jump
        if (jumping) {
            timeSinceJump += Config.msPerFrame;

            if (timeSinceJump < Config.timeJumpingUp)
                bounding.y -= Config.jumpSpeed*delta;

            // end jump
            if (onBot || timeSinceJump > Config.timeJumpingUp) {
                jumping = false;
                timeSinceJump = 0;
            }
        }
    }

    private void checkSweg() {
        if (lvl.getSwegArray() != null)
            for (Sweg sweg : lvl.getSwegArray()) {
                if (!sweg.getCollected())
                    if (bounding.intersects(sweg.getBounding())) {
                        sweg.setCollected();
                        createMessage("monies");
                        swegCollected += 1;
                    }
            }
    }

    private void checkBigMek() {
        if (lvl.getBigmekArray() != null && !lvl.getBigmekArray().getCollected())
            if (bounding.intersects(lvl.getBigmekArray().getBounding())) {
                lvl.getBigmekArray().setCollected();
                createMessage("press enter to enter lvl two");

            }

    }

    private boolean checkLvlUp(KeyHandler keyHandler) {
        if (lvl.getBigmekArray() != null)
            if (lvl.getBigmekArray().getCollected())
                if (keyHandler.getEnter()) {
                    nextAction = NextAction.LVLUP;
                    return true;

                }
        return false;
    }

    private void checkEnemies() {
        if (lvl.getEnemyArray() != null)
            for (Enemy enemy : lvl.getEnemyArray()) {
                // gegner töten
                if (enemy.getAlive())
                    if (botBounding.intersects(enemy.getTopBounding())) {
                        enemy.kill();
                        createMessage("lel rekt");
                        kills += 1;
                    }

                // feststellen ob tot
                if (alive)
                    if (bounding.intersects(enemy.getBounding()) && enemy.getAlive()) {
                        alive = false;
                        lifes -= 1;
                        if (lifes > 0)
                            createMessage("u got rekt 111  press spaec to respawn");
                        if (lifes < 1)
                            createMessage("g8 nao dis is mai end");
                    }
            }
    }

    BufferedImage getLook() {

        if (!alive)
            return lookDead;

        if (isLookingRight)
            return lookingRight;
        else return lookingLeft;
    }

    private void scroll(double delta) {
        scrollingLeft = false;
        scrollingRight = false;
        if (bounding.x > 0.6 * Config.screenX && !onLeftSide && alive) {
            scrollingRight = true;
            bounding.x -= 0.2 * delta;
        }
        if (bounding.x < 0.4 * Config.screenY && !onRightSide && alive) {
            scrollingLeft = true;
            bounding.x += 0.2 * delta;
        }
    }

    int getSwegCount() {
        return swegCollected;
    }

    int getKills() {
        return kills;
    }

    int getLifes() {
        return lifes;
    }

    private void createMessage(String messageString) {
        message = new Message(messageString);
    }

    Message getNewMessage() {
        Message messageToReturn = message;
        message = null;
        return messageToReturn;
    }

    NextAction getNextAction() {
        return nextAction;
    }

}
