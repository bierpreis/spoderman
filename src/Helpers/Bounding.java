package Helpers;


import Helpers.Scrollable;
import Helpers.Config;

import java.awt.Rectangle;

public class Bounding extends Rectangle implements Scrollable{

    public Bounding(int x,int y){
        this.x = x;
        this.y = y;
    }
    // uses by cubes
    public Bounding(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    //@Override
    public void scroll(boolean scrollingLeft, boolean scrollingRight, double delta) {

        if (scrollingLeft)
            x = x + Config.playerMoveSpeed;

        if (scrollingRight)
            x = x - Config.playerMoveSpeed;
    }


}
