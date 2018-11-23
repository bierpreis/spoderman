package map;


import java.awt.geom.Rectangle2D;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.Config;

public abstract class AbstractEnemy extends UnitGameObject {
    private boolean movingRight = true;
    private boolean alive = true;
    private int enemyMoveSpeed = Integer.parseInt(Config.get("enemyMoveSpeed"));

    String pathToImage = "resources/images/" + this.getClass().getSimpleName() + ".png";

    String pathToLookDead = "./src/resources/images/blood.png";


    public void update(Cube[][] cubeArray) {
        super.update(cubeArray);

        if (alive) {
            checkCubeCollisions(cubeArray);
            walk();
        }
    }

    public AbstractEnemy(int x, int y) {
        super(x, y);

    }

    private void setMoveDirection() {
        if (onRightSide && movingRight)
            movingRight = false;
        if (onLeftSide && !movingRight)
            movingRight = true;
    }


    private void walk() {
        setMoveDirection();
        if (movingRight) {
            bounding.x += enemyMoveSpeed;
            topBounding.x += enemyMoveSpeed;
        }
        if (!movingRight) {
            bounding.x -= enemyMoveSpeed;
            topBounding.x -= enemyMoveSpeed;
        }
    }


    public void kill() {
        try {
            this.look = ImageIO.read(new File(pathToLookDead));
        } catch (IOException e) {
            e.printStackTrace();
        }
        alive = false;
    }


    public Rectangle2D getTopBounding() {
        return topBounding;
    }

    public boolean getAlive() {
        return alive;
    }


    @Override
    protected void createLook() {
        try {
            this.look = ImageIO.read(getClass().getClassLoader().getResourceAsStream(pathToImage));
        } catch (Exception e) {
            e.printStackTrace();
        }
        bounding.width = look.getWidth();
        bounding.height = look.getHeight();
    }


}
