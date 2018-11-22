package map;

import map.UnitGameObject;

import java.awt.*;
import java.awt.image.BufferedImage;


public abstract class AbstractHat extends UnitGameObject {


    public AbstractHat(int x, int y) {

        super(x, y);
        createLook();
        createBoundings();
    }

    public boolean checkIfCollected(Rectangle playerBounding) {
        return (playerBounding.intersects(bounding));

    }

    public BufferedImage getLook() {
        return look;
    }

    public void updateBounding(Rectangle playerBounding) {
        bounding.x = playerBounding.x ;
        bounding.y = playerBounding.y - look.getHeight();
    }
}
