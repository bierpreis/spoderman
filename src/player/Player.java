package player;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


import game.Sound;
import helpers.*;
import map.*;
import map.enemies.AbstractEnemy;

public class Player extends GameObject implements Movable {



    private boolean isLookingRight = true;
    private AbstractHat hat;

    private boolean alive = true;

    private int swegCollected = 0;
    private int kills = 0;
    private int lifes = 3;

    private int timeDead = 0;

    private BufferedImage lookingLeft, lookingRight, lookDead;

    private Message message;
    private final KeyHandler keyHandler;

    private final Lvl lvl;
    private Jump jump;

    public Player(Lvl lvl, KeyHandler keyHandler) {
        super(300, 300);
        createLook();

        createBoundings();


        this.lvl = lvl;
        this.keyHandler = keyHandler;

        createMessage("nao i need to find teh bikmek");
        jump = new Jump();
        hat = new NoHat(bounding);
    }

    public void update() {
        hat.updateBounding(bounding);
        respawn();
        if (alive) {
            checkSweg();
            checkCubeCollisions(lvl.getCubes());
            checkEnemies();
            checkBigMek();
            checkHats();
            move();
            jump();
        }
    }

    private void checkHats() {
        for (AbstractHat hat : lvl.getHatList())
            if (hat.checkIfCollected(bounding))
                this.hat = hat;
    }

    private void jump() {
        if (jump.checkIfJump(keyHandler.getSpace(), onBot, onTop))
            jump.performJump(bounding);
    }

    public AbstractHat getHat() {
        return hat;
    }

    private void createLook() {

        try {
            lookingRight = ImageIO.read(getClass().getClassLoader().getResourceAsStream("img/spoder_right.png"));
            lookingLeft = ImageIO.read(getClass().getClassLoader().getResourceAsStream("img/spoder_left.png"));
            lookDead = ImageIO.read(getClass().getClassLoader().getResourceAsStream("img/blood.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //to init look
        getLook();

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


    public void move() {

        gravity(lvl.getCubes());

        if (keyHandler.getLeft() && !onLeftSide) {
            bounding.x -= Config.playerMoveSpeed;
            isLookingRight = false;
        }

        if (keyHandler.getRight() && !onRightSide) {
            bounding.x += Config.playerMoveSpeed;
            isLookingRight = true;

        }

        botBounding.x = bounding.x;
        botBounding.y = bounding.y + 20;

        updateHelpBoundings();


    }


    private void checkSweg() {

        for (Sweg sweg : lvl.getSwegList()) {
            if (!sweg.getCollected())
                if (bounding.intersects(sweg.getBounding())) {
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
                if (bounding.intersects(bigmek.getBounding())) {
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
                if (bounding.intersects(enemy.getBounding())) {

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


    @Override
    public BufferedImage getLook() {

        if (!alive)
            look = lookDead;

        if (isLookingRight)
            look = lookingRight;
        else look = lookingLeft;

        return look;
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
