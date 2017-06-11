package game;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bigmek extends Bounding {

    private boolean collected = false;
    private BufferedImage look;

    public Bigmek(int x, int y) {
	super(x, y); //warum geht das ni?!
	this.x = x;
	this.y = y;
	look = createLook();
	width = look.getWidth();
	height = look.getHeight();

    }

    public BufferedImage getLook() {
	if (!collected) {
	    return look;
	} else
	    return null;

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
    }
}
