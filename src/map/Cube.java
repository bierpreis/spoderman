package map;

import java.awt.Rectangle;

public class Cube implements Readable{


    private final Rectangle bounding;

    Cube(int x, int y, int width, int height) {
        bounding = new Rectangle(x, y, width, height);


    }


    public Rectangle getBounding() {
        return bounding;
    }

}
