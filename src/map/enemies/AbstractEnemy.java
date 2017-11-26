package map.enemies;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import helpers.Bounding;
import helpers.Config;
import map.Cube;

public abstract class AbstractEnemy extends Bounding {
    private boolean movingRight = true;
    private boolean alive = true;

    protected BufferedImage look;

    protected Bounding topBounding;


    AbstractEnemy(int x, int y) {
        super(x, y);


    }

    public void update(boolean scrollingLeft, boolean scrollingRight, Cube[] cube) {

        scroll(scrollingLeft, scrollingRight);

        if (alive) {
            walk();
            checkCubeCollisions(cube);
        }
    }

    private void checkCubeCollisions(Cube[] cubes) {
        for (Cube cube : cubes) {
            if (intersects(cube.getLeftBounding()) && movingRight)
                movingRight = false;
            if (intersects(cube.getRightBounding()) && !movingRight)
                movingRight = true;

        }
    }

    private void walk() {
        if (movingRight) {
            x += Config.enemyMoveSpeed;
            topBounding.x += Config.enemyMoveSpeed;
        }
        if (!movingRight) {
            x -= Config.enemyMoveSpeed;
            topBounding.x -= Config.enemyMoveSpeed;
        }
    }

    public void scroll(boolean scrollingLeft, boolean scrollingRight) {

        super.scroll(scrollingLeft, scrollingRight);
        topBounding.scroll(scrollingLeft, scrollingRight);
    }

    public void kill() {
        try {
            look = ImageIO.read(getClass().getClassLoader().getResourceAsStream("img/blood.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        alive = false;
    }

    public BufferedImage getLook() {

        return look;
    }



    public Rectangle2D getTopBounding() {
        return topBounding;
    }

    public boolean getAlive() {
        return alive;
    }


}
