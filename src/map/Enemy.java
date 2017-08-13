package map;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import general.Bounding;
import general.Config;

public class Enemy {
    private boolean movingRight = true;
    private boolean alive = true;

    private BufferedImage look;

    private Bounding bounding;
    private Bounding topBounding;

    private final Cube[] cube;

    // konstruktor
    public Enemy(int x, int y, String type, Cube[] cube) {

        look = createLook(type);

        bounding = new Bounding(x, y, look.getWidth(), look.getHeight());
        topBounding = new Bounding(x + 5, y, look.getWidth() - 10, 10);
        this.cube = cube;

    }

    // update
    public void update(boolean scrollingLeft, boolean scrollingRight) {

        scroll(scrollingLeft, scrollingRight);

        if (!alive)
            return;

        walk();
        checkCubeCollisions();
    }

    private void checkCubeCollisions() {
        for (int i = 0; i < cube.length; i++) {

            if (bounding.intersects(cube[i].getLeftBounding()) && movingRight) {
                movingRight = false;

            }
            if (bounding.intersects(cube[i].getRightBounding()) && !movingRight) {
                movingRight = true;


            }
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

    private void scroll(boolean scrollingLeft, boolean scrollingRight) {
        bounding = bounding.scroll(scrollingLeft, scrollingRight);
        topBounding = topBounding.scroll(scrollingLeft, scrollingRight);
    }

    public void kill() {
        try {
            look = ImageIO.read(getClass().getClassLoader().getResourceAsStream("img/blood.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        alive = false;
    }

    public BufferedImage getLook() {

        return look;
    }

    private BufferedImage createLook(String type) {

        try {
            if (type == "dolan")
                look = ImageIO.read(getClass().getClassLoader().getResourceAsStream("img/Dolan.png"));
            if (type == "gooby")
                look = ImageIO.read(getClass().getClassLoader().getResourceAsStream("img/gooby.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return look;
    }

    public Rectangle getTopBounding() {
        return topBounding;
    }

    public boolean getAlive() {
        return alive;
    }

    public Bounding getBounding() {
        return bounding;
    }

}
