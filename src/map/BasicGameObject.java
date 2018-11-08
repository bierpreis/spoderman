package map;


import java.awt.*;

public abstract class BasicGameObject {


    protected Rectangle bounding;


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


    public Rectangle getBounding() {
        return bounding;
    }


    public String toText() {
        return this.getClass().getSimpleName() + " " + bounding.x + " " + bounding.y;
    }


}
