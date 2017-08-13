package game;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import general.Bounding;
import general.Config;
import general.KeyHandler;
import general.Message;
import map.Lvl;

class Player {

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

    private Message message;

    private final Lvl lvl;

    public Player(Lvl lvl) {

        createLook();

        bounding = new Bounding((int) f_posx, (int) f_posy, lookingLeft.getWidth(), lookingLeft.getHeight());
        botBounding = new Bounding((int) f_posx, (int) f_posy + 20, lookingLeft.getWidth(), 20);

        this.lvl = lvl;

        createMessage("nao i need to find teh bikmek");
    }

    public String update(KeyHandler keyHandler) {

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

        if (checkIfEscape(keyHandler))
            return "EXIT";

        if (getLvlUp(keyHandler))
            return "LVL_UP";

        return "CONTINUE";

    }

    private boolean checkIfEscape(KeyHandler keyHandler) {

        if (showEscDialog) {
            long nextActionTime = System.currentTimeMillis() + Config.escTime;

            while (System.currentTimeMillis() < nextActionTime) {

                if (keyHandler.getEnter())
                    return true;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
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

    public Rectangle getBounding() {
        return bounding;
    }

    private void respawn(KeyHandler keyHandler) {
        if (alive)
            return;
        if (lifes < 0)
            return;

        timeDead += Config.getMsPerFrame();

        if (keyHandler.getSpace() && timeDead > Config.getPlayerRespawnTime()) {
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
        for (int i = 0; i < lvl.getCubes().length; i++) {
            // überprüft onleftside
            if (bounding.intersects(lvl.getCubes()[i].getLeftBounding())) {
                onLeftSide = true;

            }
            // überprüft onrightside
            if (bounding.intersects(lvl.getCubes()[i].getRightBounding())) {
                onRightSide = true;
            }

            // überprüft onTop
            if (botBounding.intersects(lvl.getCubes()[i].getTopBounding())) {
                onTop = true;
            }

            // überprüft ob onBot
            if (bounding.intersects(lvl.getCubes()[i].getBotBounding())) {
                onBot = true;
            }

        }

    }

    private void move(KeyHandler keyHandler) {

        if (alive) {
            if (keyHandler.getLeft() && !onRightSide && !scrollingLeft) {
                f_posx -= Config.getPlayerMoveSpeed();
                isLookingRight = false;
            }

            if (keyHandler.getRight() && !onLeftSide && !scrollingRight) {
                f_posx += Config.getPlayerMoveSpeed();
                isLookingRight = true;
            }
        }
        // schwerkraft
        if (!onTop)
            f_posy += Config.getGravity();

    }

    private void jump(boolean spaceIsPressed) {
        if (!alive)
            return;
        //start new jump
        if (onTop && !onBot && spaceIsPressed)
            jumping = true;

        //actual jump
        if (jumping) {
            timeSinceJump += Config.getMsPerFrame();

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
        if (lvl.getSweg() != null)
            for (int i = 0; i < lvl.getSweg().length; i++) {
                if (!lvl.getSweg()[i].getCollected())
                    if (bounding.intersects(lvl.getSweg()[i].getBounding())) {
                        lvl.getSweg()[i].setCollected();
                        createMessage("monies");
                        swegCollected += 1;
                    }
            }
    }

    private void checkBigMek() {
        if (lvl.getBigmek() != null && !lvl.getBigmek().getCollected())
            if (bounding.intersects(lvl.getBigmek().getBounding())) {
                lvl.getBigmek().setCollected();
                createMessage("press enter to enter lvl two");

            }

    }

    private boolean getLvlUp(KeyHandler keyHandler) {
        if (lvl.getBigmek() == null)
            return false;
        return (lvl.getBigmek().getCollected() && keyHandler.getEnter());
    }

    private void checkEnemies() {
        if (lvl.getEnemy() != null)
            for (int i = 0; i < lvl.getEnemy().length; i++) {
                // gegner töten
                if (lvl.getEnemy()[i].getAlive())
                    if (botBounding.intersects(lvl.getEnemy()[i].getTopBounding())) {
                        lvl.getEnemy()[i].kill();
                        createMessage("lel rekt");
                        kills += 1;
                    }

                // feststellen ob tot
                if (alive)
                    if (bounding.intersects(lvl.getEnemy()[i].getBounding()) && lvl.getEnemy()[i].getAlive()) {
                        alive = false;
                        lifes -= 1;
                        if (lifes > 0)
                            createMessage("u got rekt 111  press spaec to respawn");
                        if (lifes < 1)
                            createMessage("g8 nao dis is mai end");
                    }
            }
    }

    public BufferedImage getLook() {

        if (!alive)
            return lookDead;

        if (isLookingRight)
            return lookingRight;
        else return lookingLeft;
    }

    private void scroll() {
        scrollingLeft = false;
        scrollingRight = false;
        if (bounding.x > 0.6 * Config.getScreenX() && !onLeftSide && alive) {
            scrollingRight = true;
            f_posx -= 0.2;
        }
        if (bounding.x < 0.4 * Config.getScreenX() && !onRightSide && alive) {
            scrollingLeft = true;
            f_posx += (0.2);
        }
    }

    public int getSwegCount() {
        return swegCollected;
    }

    public int getKills() {
        return kills;
    }

    public int getLifes() {
        return lifes;
    }

    private void createMessage(String messageString) {
        message = new Message(messageString);
    }

    public Message getNewMessage() {
        Message messageToReturn = message;
        message = null;
        return messageToReturn;
    }

}
