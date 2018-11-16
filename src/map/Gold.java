package map;

import java.awt.*;

public class Gold extends UnitGameObject implements Collectable {

    private boolean isCollected = false;

    public Gold(int x, int y) {
        super(x, y);
        createLook();
        createBoundings();
    }


    public boolean checkIfCollected(Rectangle playerBounding) {
        if (playerBounding.intersects(bounding)) {
            isCollected = true;
            return true;
        }
        return false;

    }

    public boolean getCollected() {
        return isCollected;
    }

    public void setCollected() {
        isCollected = true;
        look = null;
    }

}
