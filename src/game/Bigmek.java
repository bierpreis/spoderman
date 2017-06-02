package game;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bigmek extends Rectangle {

    private boolean collected = false;
    private BufferedImage look;
    private Rectangle bounding;

    public Bigmek(int x, int y) {
	this.x = x;
	this.y = y;
	bounding = new Rectangle(x, y, getLook().getWidth(), getLook().getHeight());

    }

    public BufferedImage getLook() {
	if (!collected) {
	    try {
		look = ImageIO.read(getClass().getClassLoader().getResourceAsStream("img/bigmek.png"));
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	} else
	    look = null;

	return look;
    }

    public Rectangle update(boolean scrollingLeft, boolean scrollingRight) {
	if (scrollingLeft)
	    bounding.x += 5;
	if (scrollingRight)
	    bounding.x -= 5;
	return getBounding();
    }

    public Rectangle getBounding() {
	return bounding;
    }

    public boolean getCollected() {
	return collected;
    }

    public void setCollected() {
	collected = true;
    }
}
