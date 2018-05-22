package player;


import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


import game.Sound;
import helpers.*;
import map.Bigmek;
import map.Cube;
import map.Lvl;
import map.Sweg;
import map.enemies.AbstractEnemy;

public class Player implements Movable {

    private Bounding bounding;

    private boolean onTop, onBot, onRightSide, onLeftSide;

    private boolean isLookingRight = true;

    private boolean scrollingRight, scrollingLeft;

    private boolean alive = true;

    private int swegCollected = 0;
    private int kills = 0;
    private int lifes = 3;

    private int timeDead = 0;

    private final Bounding botBounding;

    private BufferedImage lookingLeft, lookingRight, lookDead;

    private Message message;
    private final KeyHandler keyHandler;

    private final Lvl lvl;
    private Jump jump;

    public Player(Lvl lvl, KeyHandler keyHandler) {
        createLook();
        bounding = new Bounding(300, 300, lookingLeft.getWidth(), lookingLeft.getHeight());


        botBounding = new Bounding(bounding.x - 5, bounding.y + 20, lookingLeft.getWidth() + 10, 20);

        this.lvl = lvl;
        this.keyHandler = keyHandler;

        createMessage("nao i need to find teh bikmek");
        jump = new Jump();
    }

    public void update() {
        respawn();
        checkIfScroll();
        if (alive) {

            checkSweg();
            checkCubeCollisions();
            checkEnemies();
            checkBigMek();
            move();
            if (jump.checkIfJump(keyHandler.getSpace(), onBot, onTop))
                jump.performJump(bounding);
        }

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

    public boolean getScrollingRight() {
        return scrollingRight;
    }

    public boolean getScrollingLeft() {
        return scrollingLeft;
    }

    private void respawn() {
        if (!alive && lifes > 0) {

            timeDead += Config.msPerFrame;

            if (keyHandler.getSpace() && timeDead > Config.playerRespawnTime) {
                alive = true;

                createMessage("respawn  now am angri as fuk");
                timeDead = 0;
            }
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

    public void move() {

        if (!onTop) bounding.y += Config.gravity;

        if (keyHandler.getLeft() && !onRightSide && !scrollingLeft) {
            bounding.x -= Config.playerMoveSpeed;
            isLookingRight = false;
        }

        if (keyHandler.getRight() && !onLeftSide && !scrollingRight) {
            bounding.x += Config.playerMoveSpeed;
            isLookingRight = true;

        }

        botBounding.x = bounding.x;
        botBounding.y = bounding.y + 20;

    }


    private void checkSweg() {

        for (Sweg sweg : lvl.getSwegList()) {
            if (!sweg.getCollected())
                if (bounding.intersects(sweg)) {
                    sweg.setCollected();
                    createMessage("monies");
                    swegCollected += 1;
                    Sound.MONEY.play();
                }
        }
    }

    private void checkBigMek() {
        for (Bigmek bigmek : lvl.getBigmekList()) {
            if (!bigmek.getCollected())
                if (bounding.intersects(bigmek)) {
                    bigmek.setCollected();
                    Sound.BIGMEK.play();
                    createMessage("press enter to enter lvl two", true);

                }
        }
    }


    private void checkEnemies() {

        for (AbstractEnemy enemy : lvl.getEnemyList()) {

            if (enemy.getAlive()) {

                // gegner töten
                if (botBounding.intersects(enemy.getTopBounding())) {
                    enemy.kill();
                    createMessage("lel rekt");
                    kills += 1;
                    Sound.ENEMY_KILLED.play();
                    return; //this ensures player wont be killed after he killed enemy
                }

                // feststellen ob tot
                if (bounding.intersects(enemy)) {

                    alive = false;
                    lifes -= 1;
                    if (lifes > 0) {
                        createMessage("u got rekt 111  press spaec to respawn");
                        Sound.PLAYER_KILLED.play();
                    }
                    if (lifes < 1) {
                        Sound.PLAYER_KILLED.play();
                        Sound.PLAYER_DEAD.play();
                        createMessage("g8 nao dis is mai end");
                    }
                }
            }
        }
    }

    public Bounding getBounding() {
        return bounding;
    }

    public BufferedImage getLook() {

        if (!alive)
            return lookDead;

        if (isLookingRight)
            return lookingRight;
        else return lookingLeft;
    }

    private void checkIfScroll() {
        scrollingLeft = false;
        scrollingRight = false;
        if (bounding.x > 0.6 * Config.screenX && !onLeftSide && alive)
            scrollingRight = true;

        if (bounding.x < 0.4 * Config.screenY && !onRightSide && alive)
            scrollingLeft = true;
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

    public void createMessage(String messageString) {
        message = new Message(messageString);
    }

    private void createMessage(String messageString, boolean isMessageFinal) {
        message = new Message(messageString, isMessageFinal);
    }

    public Message pollNewMessage() {
        Message messageToReturn = message;
        message = null;
        return messageToReturn;
    }


}
