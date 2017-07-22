package map;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import general.Bounding;

public class Bigmek{

    private boolean collected = false;
    private BufferedImage look;
    private Bounding bounding;

    public Bigmek(int x, int y) {	
	look = createLook();
	bounding = new Bounding(x,y,look.getWidth(), look.getHeight());
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
    
    public Bounding getBounding(){
	return bounding;
    }
}
