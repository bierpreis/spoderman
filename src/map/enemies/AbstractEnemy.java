package map.enemies;


import java.awt.geom.Rectangle2D;

import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import helpers.Config;
import map.GameObject;
import map.Cube;
import map.Readable;

public abstract class AbstractEnemy extends GameObject implements Readable {
    private boolean movingRight = true;
    private boolean alive = true;

    public void update(List<Cube> cubeList) {


        if (alive) {
            walk();
            checkCubeCollisions(cubeList);
        }
    }

    public AbstractEnemy(int x, int y) {
        super(x, y);

    }


    private void checkCubeCollisions(List<Cube> cubeList) {
        for (Cube cube : cubeList) {
            if (rightBounding.intersects(cube.getBounding()) && movingRight)
                movingRight = false;
            if (leftBounding.intersects(cube.getBounding()) && !movingRight)
                movingRight = true;

        }
    }

    private void walk() {
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
