package general;


import Interfaces.Scrollable;

import java.awt.geom.Rectangle2D;

public class Bounding extends Rectangle2D.Double implements Scrollable{

    public Bounding(float x,float y){
        this.x = x;
        this.y = y;
    }
    // uses by cubes
    public Bounding(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    //@Override
    public void scroll(boolean scrollingLeft, boolean scrollingRight, double delta) {

        if (scrollingLeft)
            x = x + Config.playerMoveSpeed *delta;

        if (scrollingRight)
            x = x - Config.playerMoveSpeed *delta;
    }


}
