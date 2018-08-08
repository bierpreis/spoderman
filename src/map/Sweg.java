package map;

import helpers.Collectable;

import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Sweg extends GameObject implements Collectable {

    private boolean isCollected = false;

    Sweg(int x, int y) {
        super(x, y);

        try {
            look = ImageIO.read(getClass().getClassLoader().getResourceAsStream("img/gold.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        createBoundings();


    }


    public boolean checkIfCollected(Rectangle playerBounding) {
        if (playerBounding.intersects(bounding)) {
            isCollected = true;
            System.out.println("sweg was collected!!");
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
