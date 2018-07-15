package map;

import helpers.Bounding;

import java.awt.image.BufferedImage;

public class GameObject {
    protected Bounding bounding;
    protected BufferedImage look;


    protected GameObject() {

    }

    public GameObject(int x, int y) {
        bounding = new Bounding(x, y, 0, 0);

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

    public Bounding getBounding() {
        return bounding;
    }

}
