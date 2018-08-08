package map;

import helpers.Config;


import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;

public abstract class GameObject implements Readable {

    protected boolean onTop, onBot, onRightSide, onLeftSide;


    int speed = 0;
    protected Rectangle bounding;
    protected Rectangle topBounding;
    protected Rectangle botBounding;
    protected Rectangle leftBounding;
    public Rectangle rightBounding;
    protected BufferedImage look;


    public GameObject(int x, int y) {
        //TODO remove
        bounding = new Rectangle(x, y, 0, 0);

    }
    //todo implement or remove
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

    protected void updateHelpBoundings(){
        rightBounding.x = bounding.x + bounding.width/2;
        rightBounding.y = bounding.y + 5;
        leftBounding.x = bounding.x;
        leftBounding.y = bounding.y + 5;
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

    protected void move(List<Cube> cubes) {

        if (speed != 0) {
            bounding.x = bounding.x + speed;
            for (Cube cube : cubes) {
                if (leftBounding.intersects(cube.getBounding()) || rightBounding.intersects(cube.getBounding()))
                    speed = speed * -1;
            }
        }
    }

    protected void checkCubeCollisions(List<Cube> cubes) {
        onRightSide = false;
        onLeftSide = false;
        onBot = false;
        onTop = false;
        for (Cube cube : cubes) {
            if (leftBounding.intersects(cube.getBounding()))
                onLeftSide = true;
            if (rightBounding.intersects(cube.getBounding()))
                onRightSide = true;
            if (topBounding.intersects(cube.getBounding()))
                onTop = true;
            if (botBounding.intersects(cube.getBounding()))
                onBot = true;
        }

    }


}
