package player;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.List;
import java.util.Optional;


import game.Config;
import game.KeyHandler;
import game.Audio;
import game.Sound;
import graphics.Message;
import map.*;

public class Player extends UnitGameObject {


    private boolean isLookingRight = true;
    private Optional<AbstractHat> hat;
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

        moveSpeed = Integer.parseInt(Config.get("playerMoveSpeed"));

        this.lvl = lvl;
        this.keyHandler = keyHandler;

        createMessage("nao i need to find teh bikmek");
        jump = new Jump();
        hat = Optional.empty();
    }

    public void update() {
        super.update(lvl.getCubes());
        hat.ifPresent(hat -> hat.updateBounding(bounding));
        respawn();
        updateLook();
        if (alive) {

            doUnitInteractions(lvl.getGameObjectList());

            move();
            jump();
        }
    }


    private void checkHats(AbstractHat hat) {
        if (hat.checkIfCollected(bounding))
            this.hat = Optional.of(hat);
    }

    public void drawHat(Graphics g) {
        hat.ifPresent(hat -> draw(g));
    }

    private void jump() {
        if (jump.checkIfJump(keyHandler.getSpace(), onGround, onTop))
            jump.performJump(bounding, onTop);
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
        look = lookingRight;
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
                Audio.play(Sound.MONEY);

            }
    }

    private void checkBigMek(Bigmek bigmek) {

        if (!bigmek.getCollected())
            if (bounding.intersects(bigmek.getBounding())) {
                bigmek.setCollected();
                Audio.play(Sound.BIGMEK);
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
                Audio.play(Sound.ENEMY_KILLED);
                return; //this ensures player wont be killed after he killed enemy
            }

            // feststellen ob tot
            if (bounding.intersects(enemy.getBounding())) {
                alive = false;
                lifes -= 1;
                Audio.play(Sound.PLAYER_KILLED);
                if (lifes > 0) {
                    createMessage("u got rekt 111  press spaec to respawn");
                }
                if (lifes < 1) {
                    Audio.play(Sound.PLAYER_DEAD);
                    createMessage("g8 nao dis is mai end");
                }
            }
        }

    }

    private void updateLook() {
        if (!alive)
            look = lookDead;
        else if (isLookingRight)
            look = lookingRight;
        else look = lookingLeft;
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
