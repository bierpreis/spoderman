package map;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Bigmek extends UnitGameObject {

    private boolean collected = false;


    public Bigmek(int x, int y) {
        super(x, y);
        createLook();
        createBoundings();


    }


    public boolean getCollected() {
        return collected;
    }

    public void setCollected() {

        collected = true;
        look = null;
    }
}
