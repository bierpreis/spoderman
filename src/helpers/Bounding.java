package helpers;


import player.Scrolling;

import java.awt.Rectangle;

public class Bounding extends Rectangle implements Scrollable {


    public Bounding(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void scroll(Scrolling scrolling) {

        if (scrolling.equals(Scrolling.LEFT))
            x = x + Config.playerMoveSpeed;

        if (scrolling.equals(Scrolling.RIGHT))
            x = x - Config.playerMoveSpeed;
    }

    public void jump() {
        y = y - Config.jumpSpeed;
    }


}
