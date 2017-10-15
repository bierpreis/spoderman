package map;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import general.Bounding;

public class Sweg extends Bounding{

    private boolean isCollected = false;
    private BufferedImage look;

    Sweg(int x, int y) {
        super(x,y);

        try {
            look = ImageIO.read(getClass().getClassLoader().getResourceAsStream("img/gold.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.width = look.getWidth();
        this.height = look.getHeight();


    }

    public BufferedImage getLook() {
        return look;
    }



    public boolean getCollected() {
        return isCollected;
    }

    public void setCollected() {
        isCollected = true;
        look = null;

    }

}
