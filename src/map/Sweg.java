package map;

import helpers.Bounding;
import helpers.Drawable;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Sweg implements Drawable {

    private boolean isCollected = false;
    private BufferedImage look;
    private Bounding bounding;

    Sweg(int x, int y) {


        try {
            look = ImageIO.read(getClass().getClassLoader().getResourceAsStream("img/gold.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        bounding = new Bounding(x,y,look.getWidth(), look.getHeight());


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

    //TODO: this both could be done by getbounding? but does not fit with law of demeter
    //or implement superclass?
    @Override
    public int getX() {
        return bounding.x;
    }

    @Override
    public int getY() {
        return bounding.y;
    }

    //TODO: this should be in superclass?
    public void scroll(boolean scrollingLeft, boolean scrollingRight){
        bounding.scroll(scrollingLeft, scrollingRight);
    }

    public Bounding getBounding(){
        return bounding;
    }
}
