package map;

import helpers.Collectable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Sweg extends GameObject implements Collectable {

    private boolean isCollected = false;
    private BufferedImage look;

    Sweg(int x, int y) {


        try {
            look = ImageIO.read(getClass().getClassLoader().getResourceAsStream("img/gold.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        bounding = new Rectangle(x, y, look.getWidth(), look.getHeight());


    }

    public BufferedImage getLook() {
        return look;
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

    //TODO: this both could be done by getbounding? but does not fit with law of demeter
    //or implement superclass?


    //TODO: this should be in superclass?


    public Rectangle getBounding() {
        return bounding;
    }
}
