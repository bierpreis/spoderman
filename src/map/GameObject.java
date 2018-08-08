package map;

import helpers.Config;


import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;

public abstract class GameObject implements Readable {


    int speed = 0;
    protected Rectangle bounding;
    protected Rectangle topBounding;
    protected Rectangle botBounding;
    protected Rectangle leftBounding;
    protected Rectangle rightBounding;
    protected BufferedImage look;


    public GameObject(int x, int y) {
        bounding = new Rectangle(x, y, 0, 0);

    }

    public void tick(List<Cube> cubes) {
        gravity(cubes);
        move(cubes);
    }

    protected void createBoundings() {
        bounding = new Rectangle(bounding.x, bounding.y, look.getWidth(), look.getHeight());
        topBounding = new Rectangle(bounding.x + 5, bounding.y, bounding.width - 10, bounding.height / 2);
        botBounding = new Rectangle(bounding.x + 5, bounding.y + bounding.height / 2, bounding.width - 10, bounding.height / 2);
        leftBounding = new Rectangle(bounding.x, bounding.y + 5, bounding.width / 2, bounding.height - 10);
        rightBounding = new Rectangle(bounding.x + bounding.width / 2, bounding.y + 5, bounding.width / 2, bounding.height - 10);

    }


    public int getX() {
        return bounding.x;
    }


    public int getY() {
        return bounding.y;
    }

    public BufferedImage getLook() {
        return look;
    }

    public Rectangle getBounding() {
        return bounding;
    }

    protected void gravity(List<Cube> cubes) {
        boolean falling = true;
        for (Cube cube : cubes) {
            if (botBounding.intersects(cube.getBounding()))
                falling = false;
        }

        if (falling)
            bounding.y = bounding.y + Config.gravity;

    }

    private void move(List<Cube> cubes) {
        if (speed != 0) {
            bounding.x = bounding.x + speed;
            for (Cube cube : cubes) {
                if (leftBounding.intersects(cube.getBounding()) || rightBounding.intersects(cube.getBounding()))
                    speed = speed * -1;
            }
        }
    }

}
