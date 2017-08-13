package map;

import general.Bounding;

public class Cube {

    private final Bounding bounding;
    private final Bounding topBounding;
    private final Bounding botBounding;
    private final Bounding leftBounding;
    private final Bounding rightBounding;

    public Cube(int x, int y, int width, int height) {

        bounding = new Bounding(x, y, width, height);
        topBounding = new Bounding(x, y-5, width, 10);
        botBounding = new Bounding(x + 6, y + 30, width - 12, height - 30);
        leftBounding = new Bounding(x + 5, y + 10, 5, height - 20);
        rightBounding = new Bounding(x + width - 5, y + 10, 5, height - 20);

    }

    public void updateBounding(boolean scrollingLeft, boolean scrollingRight) {

        bounding.scroll(scrollingLeft, scrollingRight);
        topBounding.scroll(scrollingLeft, scrollingRight);
        botBounding.scroll(scrollingLeft, scrollingRight);
        leftBounding.scroll(scrollingLeft, scrollingRight);
        rightBounding.scroll(scrollingLeft, scrollingRight);

    }

    public Bounding getBounding() {
        return bounding;
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
