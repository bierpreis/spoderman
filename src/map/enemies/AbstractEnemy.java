package map.enemies;


import java.awt.geom.Rectangle2D;

import java.io.IOException;

import javax.imageio.ImageIO;

import helpers.Config;
import map.BasicGameObject;
import map.Cube;
import map.UnitGameObject;

public abstract class AbstractEnemy extends UnitGameObject {
    private boolean movingRight = true;
    private boolean alive = true;


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
            bounding.x += Config.enemyMoveSpeed;
            topBounding.x += Config.enemyMoveSpeed;
        }
        if (!movingRight) {
            bounding.x -= Config.enemyMoveSpeed;
            topBounding.x -= Config.enemyMoveSpeed;
        }
    }


    public void kill() {
        try {
            look = ImageIO.read(getClass().getClassLoader().getResourceAsStream("img/blood.png"));
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

    String pathToImage = "img/" + this.getClass().getSimpleName() + ".png";

    protected void createLook() {
        try {
            this.look = ImageIO.read(getClass().getClassLoader().getResourceAsStream(pathToImage));
        } catch (IOException ioe) {
            System.out.println(pathToImage + " not found!");
        }
        bounding.width = look.getWidth();
        bounding.height = look.getHeight();
    }




}
