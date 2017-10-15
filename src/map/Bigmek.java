package map;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import general.Bounding;

public class Bigmek extends Bounding{

    private boolean collected = false;
    private BufferedImage look;


    Bigmek(int x, int y) {
        super(x, y);
        look = createLook();
        this.width = look.getWidth(); this.height = look.getHeight();

    }

    public BufferedImage getLook() {
        return look;
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
