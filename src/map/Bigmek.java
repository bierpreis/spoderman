package map;

import helpers.Bounding;
import helpers.Drawable;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bigmek implements Drawable {

    private boolean collected = false;
    private BufferedImage look;
    private Bounding bounding;


    Bigmek(int x, int y) {
        look = createLook();
        bounding = new Bounding(x,y,look.getWidth(), look.getHeight());


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


    public int getX(){
        return bounding.x;
    }

    public int getY(){
        return bounding.y;
    }

    public BufferedImage getLook(){
        return look;
    }

    public Bounding getBounding(){
        return bounding;
    }
}
