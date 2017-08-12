package map;

import general.Bounding;

public class Cube {

    private final Bounding bounding;
    private Bounding topBounding;
    private Bounding botBounding;
    private Bounding leftBounding;
    private Bounding rightBounding;

    public Cube(int x, int y, int width, int height) {

        bounding = new Bounding(x, y, width, height);

        topBounding = new Bounding(x, y, width, 15);

        botBounding = new Bounding(x + 6, y + 25, width - 12, height - 25);

        leftBounding = new Bounding(x + 10, y + 10, 5, height - 20);
        rightBounding = new Bounding(x + width - 10, y + 10, 5, height - 20);

    }

    public void updateBounding(boolean scrollingLeft, boolean scrollingRight) {

        bounding.scroll(scrollingLeft, scrollingRight);

        topBounding = topBounding.scroll(scrollingLeft, scrollingRight);
        botBounding = botBounding.scroll(scrollingLeft, scrollingRight);

        leftBounding = leftBounding.scroll(scrollingLeft, scrollingRight);
        rightBounding = rightBounding.scroll(scrollingLeft, scrollingRight);

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
