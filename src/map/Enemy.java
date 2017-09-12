package map;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import general.Bounding;
import general.Config;

public class Enemy {
    private boolean movingRight = true;
    private boolean alive = true;

    private BufferedImage look;

    private final Bounding bounding;
    private final Bounding topBounding;

    private final Cube[] cube;
    
    Enemy(int x, int y, String type, Cube[] cube) {

        look = createLook(type);

        bounding = new Bounding(x, y, look.getWidth(), look.getHeight());
        topBounding = new Bounding(x + 5, y, look.getWidth() - 10, 10);
        this.cube = cube;

    }

    void update(boolean scrollingLeft, boolean scrollingRight, double delta) {

        scroll(scrollingLeft, scrollingRight, delta);

        if(alive) {
            walk();
            checkCubeCollisions();
        }
    }

    private void checkCubeCollisions() {
        for (Cube cube : cube) {
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

    private void scroll(boolean scrollingLeft, boolean scrollingRight, double delta) {
        bounding.scroll(scrollingLeft, scrollingRight, delta);
        topBounding.scroll(scrollingLeft, scrollingRight, delta);
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
            if (type.equals("dolan"))
                look = ImageIO.read(getClass().getClassLoader().getResourceAsStream("img/Dolan.png"));
            if (type.equals("gooby"))
                look = ImageIO.read(getClass().getClassLoader().getResourceAsStream("img/gooby.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return look;
    }

    public Rectangle2D.Double getTopBounding() {
        return topBounding;
    }

    public boolean getAlive() {
        return alive;
    }

    public Bounding getBounding() {
        return bounding;
    }

}
