package player;

import helpers.Collectable;
import map.UnitGameObject;

import java.awt.*;
import java.awt.image.BufferedImage;


public abstract class AbstractHat extends UnitGameObject implements Collectable {


    public AbstractHat(int x, int y) {

        super(x, y);
        createLook();
        createBoundings();
    }

    @Override
    public boolean checkIfCollected(Rectangle playerBounding) {
        return (playerBounding.intersects(bounding));

    }

    public BufferedImage getLook() {
        return look;
    }


    protected void createBounding(Rectangle playerBounding, BufferedImage look) {
        this.bounding = new Rectangle(playerBounding.x, playerBounding.y - 100, look.getWidth(), look.getHeight());
    }

    public void updateBounding(Rectangle playerBounding) {
        bounding.x = playerBounding.x;
        bounding.y = playerBounding.y - look.getHeight() + 10;
    }
}
