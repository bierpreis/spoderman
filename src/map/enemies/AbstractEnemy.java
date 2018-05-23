package map.enemies;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import helpers.Bounding;
import helpers.Config;
import helpers.Drawable;
import map.Cube;

public abstract class AbstractEnemy implements Drawable {
    private boolean movingRight = true;
    private boolean alive = true;

    protected BufferedImage look;

    protected Bounding topBounding;
    protected Bounding bounding;


    AbstractEnemy(int x, int y) {
        bounding = new Bounding(x, y, 0, 0);


    }

    public void update(boolean scrollingLeft, boolean scrollingRight, List<Cube> cubeList) {

        scroll(scrollingLeft, scrollingRight);

        if (alive) {
            walk();
            checkCubeCollisions(cubeList);
        }
    }

    public int getX() {
        return bounding.x;
    }

    public int getY() {
        return bounding.y;
    }

    public Bounding getBounding() {
        return bounding;
    }


    private void checkCubeCollisions(List<Cube> cubeList) {
        for (Cube cube : cubeList) {
            if (bounding.intersects(cube.getLeftBounding()) && movingRight)
                movingRight = false;
            if (bounding.intersects(cube.getRightBounding()) && !movingRight)
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

    public void scroll(boolean scrollingLeft, boolean scrollingRight) {

        bounding.scroll(scrollingLeft, scrollingRight);
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
