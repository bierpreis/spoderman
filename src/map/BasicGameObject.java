package map;

import helpers.Config;


import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;

public abstract class BasicGameObject {

    protected boolean onTop, onGround, onRightSide, onLeftSide;


    private int speed = 0;
    protected Rectangle bounding;
    public Rectangle topBounding;
    protected Rectangle botBounding;
    private Rectangle leftBounding;
    private Rectangle rightBounding;
    protected BufferedImage look;


    public BasicGameObject() {

    }

    public BasicGameObject(int x, int y) {
        super();
        bounding = new Rectangle(x, y, 0, 0);

    }


    protected void createBoundings() {
        bounding = new Rectangle(bounding.x, bounding.y, look.getWidth(), look.getHeight());
        topBounding = new Rectangle(bounding.x + 5, bounding.y - 5, bounding.width - 10, bounding.height / 2);
        botBounding = new Rectangle(bounding.x + 5, bounding.y + bounding.height / 2, bounding.width - 10, bounding.height / 2);
        leftBounding = new Rectangle(bounding.x, bounding.y + 5, bounding.width / 2, bounding.height - 10);
        rightBounding = new Rectangle(bounding.x + bounding.width / 2, bounding.y + 5, bounding.width / 2, bounding.height - 10);


    }

    protected void updateHelpBoundings() {
        rightBounding.x = bounding.x + bounding.width / 2;
        rightBounding.y = bounding.y + 5;
        leftBounding.x = bounding.x;
        leftBounding.y = bounding.y + 5;
        topBounding.x = bounding.x;
        topBounding.y = bounding.y - 10;
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

    protected void gravity(Cube[][] cubeArray) {
        boolean falling = true;
        for (int cubeY = 0; cubeY < cubeArray.length; cubeY++)
            for (Cube cube : cubeArray[cubeY]) {
                if (botBounding.intersects(cube.getBounding()))
                    falling = false;
            }

        if (falling)
            bounding.y = bounding.y + Config.gravity;

    }

    //todo
    protected void move(List<Cube> cubes) {

        if (speed != 0) {
            bounding.x = bounding.x + speed;
            for (Cube cube : cubes) {
                if (leftBounding.intersects(cube.getBounding()) || rightBounding.intersects(cube.getBounding()))
                    speed = speed * -1;
            }
        }
    }

    abstract public String toText();

    protected void checkCubeCollisions(Cube[][] cubeArray) {
        onRightSide = false;
        onLeftSide = false;
        onGround = false;
        onTop = false;
        for (int cubeY = 0; cubeY < cubeArray.length; cubeY++) {
            for (Cube cube : cubeArray[cubeY]) {
                if (leftBounding.intersects(cube.getBounding()))
                    onLeftSide = true;
                if (rightBounding.intersects(cube.getBounding()))
                    onRightSide = true;
                if (topBounding.intersects(cube.getBounding()))
                    onTop = true;
                if (botBounding.intersects(cube.getBounding()))
                    onGround = true;
            }
        }

    }


}