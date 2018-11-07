package map;

import helpers.Config;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public abstract class BasicGameObject {

    protected boolean onTop, onGround, onRightSide, onLeftSide;


    private int speed = 0;
    protected Rectangle bounding;
    protected BufferedImage look;


    public BasicGameObject(int x, int y) {
        bounding = new Rectangle(x, y, 0, 0);

    }


    public abstract void draw(Graphics g);



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


    abstract public String toText();


}
