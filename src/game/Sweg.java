package game;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

class Sweg extends Rectangle {

    private boolean isCollected = false;
    private BufferedImage look;
    private Rectangle bounding;

    public Sweg(int x, int y) {

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

    public Rectangle getBounding() {
	return bounding;
    }

    public void updateBounding(boolean scrollingLeft, boolean scrollingRight) {

	if (!isCollected) {
	    if (scrollingLeft)
		bounding.x += 5;
	    if (scrollingRight)
		bounding.x -= 5;
	}

    }

    public boolean isCollected() {
	return isCollected;
    }

    public void setCollected() {
	isCollected = true;
	look = null;

    }

}
