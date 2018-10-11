package map;

import java.awt.*;

public class Cube implements Readable {


    private final Rectangle bounding;
    private final static int size = 32;
    private boolean active = false;

    public Cube(int x, int y) {
        bounding = new Rectangle(x * size, y * size, size, size);
    }

    Cube(int x, int y, int width, int height) {
        bounding = new Rectangle(x, y, width, height);


    }


    public Rectangle getBounding() {
        return bounding;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean isActive) {
        this.active = isActive;
    }

    public static int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Cube: " + bounding;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        if (active)
            g.fillRect(bounding.x, bounding.y, size, size);
        else g.drawRect(bounding.x, bounding.y, size, size);
    }
}
