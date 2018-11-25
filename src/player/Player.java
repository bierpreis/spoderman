package player;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.List;


import game.Config;
import game.KeyHandler;
import game.Sound;
import graphics.Message;
import map.*;
import map.AbstractEnemy;

public class Player extends UnitGameObject {


    private boolean isLookingRight = true;
    private AbstractHat hat;
    private int msPerFrame = Integer.parseInt(Config.get("msPerFrame"));

    private boolean alive = true;
    private int playerRespawnTime = Integer.parseInt(Config.get("playerRespawnTime"));

    private int goldCollected = 0;
    private int kills = 0;
    private int lifes = 3;
    private int moveSpeed;

    private int timeDead = 0;

    private BufferedImage lookingLeft, lookingRight, lookDead;

    private Message message;
    private final KeyHandler keyHandler;

    private final Lvl lvl;
    private Jump jump;

    public Player(Lvl lvl, KeyHandler keyHandler) {
        super(0, 30);
        createLook();

        createBoundings();

        moveSpeed =  Integer.parseInt(Config.get("playerMoveSpeed"));

        this.lvl = lvl;
        this.keyHandler = keyHandler;

        createMessage("nao i need to find teh bikmek");
        jump = new Jump();
        hat = new NoHat(bounding.x, bounding.y);
    }

    public void update() {
        super.update(lvl.getCubes());
        hat.updateBounding(bounding);
        respawn();
        updateLook();
        if (alive) {

            doUnitInteractions(lvl.getGameObjectList());

            move();
            jump();
        }
    }

    private void updateLook() {
        if (!alive)
            look = lookDead;
        else if (isLookingRight)
            look = lookingRight;
        else look = lookingLeft;
    }

    private void checkHats(AbstractHat hat) {
        if (hat.checkIfCollected(bounding))
            this.hat = hat;
    }

    public void drawHat(Graphics g) {
        hat.draw(g);
    }

    private void jump() {
        if (jump.checkIfJump(keyHandler.getSpace(), onGround, onTop))
            jump.performJump(bounding, onTop);
    }

    public AbstractHat getHat() {
        return hat;
    }

    @Override
    protected void createLook() {

        try {
            lookingRight = ImageIO.read(getClass().getClassLoader().getResourceAsStream("resources/images/spoder_right.png"));
            lookingLeft = ImageIO.read(getClass().getClassLoader().getResourceAsStream("resources/images/spoder_left.png"));
            lookDead = ImageIO.read(getClass().getClassLoader().getResourceAsStream("resources/images/blood.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        getLook();

    }


    private void respawn() {
        if (!alive && lifes > 0) {

            timeDead += msPerFrame;

            if (keyHandler.getSpace() && timeDead > playerRespawnTime) {
                alive = true;

                createMessage("respawn  now am angri as fuk");
                timeDead = 0;
            }
        }
    }


    private void move() {

        gravity(lvl.getCubes());

        if (keyHandler.getLeft() && !onLeftSide) {
            bounding.x -= moveSpeed;
            isLookingRight = false;
        }

        if (keyHandler.getRight() && !onRightSide) {
            bounding.x += moveSpeed;
            isLookingRight = true;

        }


    }

    private void doUnitInteractions(List<UnitGameObject> gameObjectList) {
        for (UnitGameObject gameObject : gameObjectList) {
            if (gameObject instanceof Gold)
                checkGold((Gold) gameObject);
            if (gameObject instanceof AbstractEnemy)
                checkEnemies((AbstractEnemy) gameObject);
            if (gameObject instanceof Bigmek)
                checkBigMek((Bigmek) gameObject);
            if (gameObject instanceof AbstractHat)
                checkHats((AbstractHat) gameObject);
        }
    }


    private void checkGold(Gold gold) {
        if (!gold.getCollected())
            if (bounding.intersects(gold.getBounding())) {
                gold.setCollected();
                createMessage("monies");
                goldCollected += 1;
                Sound.MONEY.play();

            }
    }

    private void checkBigMek(Bigmek bigmek) {

        if (!bigmek.getCollected())
            if (bounding.intersects(bigmek.getBounding())) {
                bigmek.setCollected();
                Sound.BIGMEK.play();
                createMessage("press enter to enter lvl two", true);
                lvl.setBigmekCollected();


            }
    }


    private void checkEnemies(AbstractEnemy enemy) {

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


    @Override
    public BufferedImage getLook() {
        //todo: create eneum for this?
        if (!alive)
            look = lookDead;

        if (isLookingRight)
            look = lookingRight;
        else look = lookingLeft;

        return look;
    }

    public int getGoldCount() {
        return goldCollected;
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
