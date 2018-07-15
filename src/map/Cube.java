package map;

import helpers.Bounding;

public class Cube extends GameObject {


    private final Bounding topBounding;
    private final Bounding botBounding;
    private final Bounding leftBounding;
    private final Bounding rightBounding;

    Cube(int x, int y, int width, int height) {
        bounding = new Bounding(x,y,width, height);
        topBounding = new Bounding(x, y-5, width, 10);
        botBounding = new Bounding(x + 6, y + 30, width - 12, height - 30);
        leftBounding = new Bounding(x - 5, y + 10, 5, height - 20);
        rightBounding = new Bounding(x + width - 5, y + 10, 5, height - 20);

    }


    public Bounding getTopBounding() {
        return topBounding;
    }

    public Bounding getLeftBounding() {
        return leftBounding;
    }

    public Bounding getRightBounding() {
        return rightBounding;
    }

    public Bounding getBotBounding() {
        return botBounding;
    }

}
