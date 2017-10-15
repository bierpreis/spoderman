package map;

import general.Bounding;

public class Cube extends  Bounding{


    private final Bounding topBounding;
    private final Bounding botBounding;
    private final Bounding leftBounding;
    private final Bounding rightBounding;

    Cube(int x, int y, int width, int height) {
        super(x,y, width, height);
        topBounding = new Bounding(x, y-5, width, 10);
        botBounding = new Bounding(x + 6, y + 30, width - 12, height - 30);
        leftBounding = new Bounding(x + 5, y + 10, 5, height - 20);
        rightBounding = new Bounding(x + width - 5, y + 10, 5, height - 20);

    }

    void updateBounding(boolean scrollingLeft, boolean scrollingRight, double delta) {

        scroll(scrollingLeft, scrollingRight, delta);
        topBounding.scroll(scrollingLeft, scrollingRight, delta);
        botBounding.scroll(scrollingLeft, scrollingRight, delta);
        leftBounding.scroll(scrollingLeft, scrollingRight, delta);
        rightBounding.scroll(scrollingLeft, scrollingRight, delta);

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
