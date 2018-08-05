package map;

import helpers.Config;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class GameObject {

    int speed = 0;
    protected Rectangle bounding;
    protected Rectangle topBouding;
    protected Rectangle botBounding;
    protected Rectangle leftBounding;
    protected Rectangle rightBounding;
    protected BufferedImage look;


    protected GameObject() {

    }

    public GameObject(int x, int y) {
        bounding = new Rectangle(x, y, 0, 0);

    }

    public void tick(Cube[] cubes) {
        gravity(cubes);
        move(cubes);
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

    private void gravity(Cube[] cubes) {
        for (Cube cube : cubes) {
            if (botBounding.intersects(cube.bounding))
                bounding.y = bounding.y + Config.gravity;
        }

    }

    private void move(Cube[] cubes) {
        if (speed != 0) {
            bounding.x = bounding.x + speed;
            for (Cube cube : cubes) {
                if (leftBounding.intersects(cube.bounding) || rightBounding.intersects(cube.bounding))
                    speed = speed * -1;
            }
        }
    }

}
