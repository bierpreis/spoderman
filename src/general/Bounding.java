package general;


import java.awt.geom.Rectangle2D;

public class Bounding extends Rectangle2D.Double {

    // uses by cubes
    public Bounding(int x, int y, int width, int height) {
        super((double)x, (double)y, (double)width, (double)height);
    }


    public void scroll(boolean scrollingLeft, boolean scrollingRight, double delta) {

        if (scrollingLeft)
            x = x + Config.playerMoveSpeed *delta;

        if (scrollingRight)
            x = x - Config.playerMoveSpeed *delta;
    }


}
