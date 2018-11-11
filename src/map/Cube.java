package map;

import java.awt.*;

public class Cube extends BasicGameObject {


    private final Rectangle bounding;
    private final static int size = 32;
    private boolean active = false;

    public Cube(int x, int y) {
        super(x,y);
        bounding = new Rectangle(x * size, y * size, size, size);
    }


    public Rectangle getBounding() {
        return bounding;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive() {
        active = true;
    }

    public static int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Cube: " + bounding;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        if (active)
            g.fillRect(bounding.x, bounding.y, size, size);
        else g.drawRect(bounding.x, bounding.y, size, size);
    }

    @Override
    public String toText() {
        return bounding.x + " " + bounding.y;
    }
}
