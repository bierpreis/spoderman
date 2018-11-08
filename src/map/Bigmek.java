package map;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bigmek extends UnitGameObject {

    private boolean collected = false;


    Bigmek(int x, int y) {
        super(x, y);
        createLook();
        createBoundings();


    }


    private BufferedImage createLook() {

        try {
            look = ImageIO.read(getClass().getClassLoader().getResourceAsStream("img/bigmek.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return look;

    }

    public boolean getCollected() {
        return collected;
    }

    public void setCollected() {

        collected = true;
        look = null;
    }
}
