package map;


import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


import Interfaces.Movable;
import general.Bounding;
import general.Config;
import general.KeyHandler;
import general.Message;

public class Player extends Bounding implements Movable {

    private boolean onTop, onBot, onRightSide, onLeftSide;

    private boolean isLookingRight = true;

    private boolean scrollingRight, scrollingLeft;

    private boolean alive = true;

    private int timeSinceJump;
    private boolean jumping = false;

    private int swegCollected = 0;
    private int kills = 0;
    private int lifes = 3;

    private int timeDead = 0;

    private final Bounding botBounding;

    private BufferedImage lookingLeft, lookingRight, lookDead;

    private Message message;
    private KeyHandler keyHandler;

    private final Lvl lvl;

    public Player(Lvl lvl, KeyHandler keyHandler) {
        super(300, 300);
        createLook();

        width = lookingLeft.getWidth();
        height = lookingLeft.getHeight();

        botBounding = new Bounding(x, y + 20, lookingLeft.getWidth(), 20);

        this.lvl = lvl;
        this.keyHandler = keyHandler;

        createMessage("nao i need to find teh bikmek");
    }

    public void update(double delta) {

        scroll(delta);

        checkSweg();
        checkCubeCollisions();
        checkEnemies();
        checkBigMek();

        move(keyHandler, delta);
        jump(keyHandler, delta);

        respawn(keyHandler);
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

    public boolean getScrollingRight(){
        return scrollingRight;
    }

    public boolean getScrollingLeft(){
        return scrollingLeft;
    }



    private void respawn(KeyHandler keyHandler) {
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
            if (intersects(cube.getLeftBounding()))
                onLeftSide = true;
            if (intersects(cube.getRightBounding()))
                onRightSide = true;
            if (botBounding.intersects(cube.getTopBounding()))
                onTop = true;
            if (intersects(cube.getBotBounding()))
                onBot = true;
        }

    }

    public void move(KeyHandler keyHandler, double delta) {


        if (!alive) return;

        if (!onTop)
            y += Config.gravity * delta;

        if (keyHandler.getLeft() && !onRightSide && !scrollingLeft) {
            x -= Config.playerMoveSpeed * delta;
            isLookingRight = false;
        }

        if (keyHandler.getRight() && !onLeftSide && !scrollingRight) {
            x += Config.playerMoveSpeed * delta;
            isLookingRight = true;

        }

        botBounding.x = x;
        botBounding.y = y + 20;

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
                y -= Config.jumpSpeed * delta;

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
                    if (intersects(sweg)) {
                        sweg.setCollected();
                        createMessage("monies");
                        swegCollected += 1;
                    }
            }
    }

    private void checkBigMek() {
        if (lvl.getBigmekArray() != null && !lvl.getBigmekArray().getCollected())
            if (intersects(lvl.getBigmekArray())) {
                lvl.getBigmekArray().setCollected();
                createMessage("press enter to enter lvl two");

            }

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
                    if (intersects(enemy) && enemy.getAlive()) {
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

    private void scroll(double delta) {
        scrollingLeft = false;
        scrollingRight = false;
        if (x > 0.6 * Config.screenX && !onLeftSide && alive) {
            scrollingRight = true;
            x -= 0.2 * delta;
        }
        if (x < 0.4 * Config.screenY && !onRightSide && alive) {
            scrollingLeft = true;
            x += 0.2 * delta;
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

    public void createMessage(String messageString) {
        message = new Message(messageString);
    }

    public Message pollNewMessage() {
        Message messageToReturn = message;
        message = null;
        return messageToReturn;
    }



}
