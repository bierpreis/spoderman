package map;

import java.awt.*;

public class Cube extends GameObject {


    private final Rectangle topBounding;
    private final Rectangle botBounding;
    private final Rectangle leftBounding;
    private final Rectangle rightBounding;

    Cube(int x, int y, int width, int height) {
        bounding = new Rectangle(x,y,width, height);
        topBounding = new Rectangle(x, y-5, width, 10);
        botBounding = new Rectangle(x + 6, y + 30, width - 12, height - 30);
        leftBounding = new Rectangle(x - 5, y + 10, 5, height - 20);
        rightBounding = new Rectangle(x + width - 5, y + 10, 5, height - 20);

    }


    public Rectangle getTopBounding() {
        return topBounding;
    }

    public Rectangle getLeftBounding() {
        return leftBounding;
    }

    public Rectangle getRightBounding() {
        return rightBounding;
    }

    public Rectangle getBotBounding() {
        return botBounding;
    }

}
