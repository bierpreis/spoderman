package game;

import java.awt.Rectangle;
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

class Player implements Runnable {

    private boolean onTop, onBot, onRightSide, onLeftSide;

    private boolean isLookingRight = true;

    private boolean scrollingRight, scrollingLeft;

    private boolean alive = true;

    private int timeSinceJump;
    private boolean jumping = false;

    private int swegCollected = 0;
    private int kills = 0;
    private int lifes = 3;

    private boolean showEscDialog = false;

    private int timeDead = 0;

    private float f_posx = 350; // f_ als kennzeichen für float
    private float f_posy = 300;

    private final Bounding bounding;
    private final Bounding botBounding;

    private BufferedImage lookingLeft, lookingRight, lookDead;
    private boolean lvlUp = false;

    private Message message;
    private KeyHandler keyHandler;

    private final Lvl lvl;

    Player(Lvl lvl, KeyHandler keyHandler) {

        createLook();

        bounding = new Bounding((int) f_posx, (int) f_posy, lookingLeft.getWidth(), lookingLeft.getHeight());
        botBounding = new Bounding((int) f_posx, (int) f_posy + 20, lookingLeft.getWidth(), 20);

        this.lvl = lvl;
        this.keyHandler = keyHandler;

        createMessage("nao i need to find teh bikmek");
    }

    @Override
    public void run() {

        boolean running = true;
        while (running) {
            long startTime = System.nanoTime();

            lvl.update(scrollingLeft, scrollingRight);

            checkSweg();
            checkCubeCollisions();
            checkEnemies();
            checkBigMek();

            respawn(keyHandler);
            updateBounding();
            scroll();
            move(keyHandler);
            jump(keyHandler.getSpace());

            running = (!checkIfEscape(keyHandler) && !checkLvlUp(keyHandler));


            sleep(startTime);

        }

    }

    private void sleep(long startTime) {
        long expiredTime = System.nanoTime() - startTime;
        long sleepTime = Config.msPerFrame - (expiredTime/100_000);
        //System.out.println("sleeptime in player: " + sleepTime);
        if(sleepTime>0)
        try {
            Thread.sleep(sleepTime);
        } catch (Exception e) {
            System.out.println("Interrupted while sleeping");
        }
    }

    private boolean checkIfEscape(KeyHandler keyHandler) {

        if (showEscDialog) {
            long nextActionTime = System.currentTimeMillis() + Config.escTime;

            while (System.currentTimeMillis() < nextActionTime) {

                if (keyHandler.getEnter())
                    return true;
            }
        }
        showEscDialog = false;
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

    private void updateBounding() {
        bounding.x = (int) f_posx;
        bounding.y = (int) f_posy;
        botBounding.x = (int) f_posx;
        botBounding.y = (int) f_posy + 20;

    }

    Rectangle getBounding() {
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

    private void move(KeyHandler keyHandler) {
        if (!onTop)
            f_posy += Config.gravity;

        if (!alive) return;

        if (keyHandler.getLeft() && !onRightSide && !scrollingLeft) {
            f_posx -= Config.playerMoveSpeed;
            isLookingRight = false;
        }

        if (keyHandler.getRight() && !onLeftSide && !scrollingRight) {
            f_posx += Config.playerMoveSpeed;
            isLookingRight = true;

        }

    }

    private void jump(boolean spaceIsPressed) {
        if (!alive)
            return;
        //start new jump
        if (onTop && !onBot && spaceIsPressed)
            jumping = true;

        //actual jump
        if (jumping) {
            timeSinceJump += Config.msPerFrame;

            if (timeSinceJump < Config.timeJumpingUp)
                f_posy -= Config.jumpSpeed;

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
                    lvlUp = true;
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

    private void scroll() {
        scrollingLeft = false;
        scrollingRight = false;
        if (bounding.x > 0.6 * Config.screenX && !onLeftSide && alive) {
            scrollingRight = true;
            f_posx -= 0.2;
        }
        if (bounding.x < 0.4 * Config.screenY && !onRightSide && alive) {
            scrollingLeft = true;
            f_posx += (0.2);
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

    boolean checkLvlUp() {
        return lvlUp;
    }

}
