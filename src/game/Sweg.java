package game;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

class Sweg extends Rectangle {

	private boolean notCollected = true;
	private BufferedImage look;
	private Rectangle bounding;

	public Sweg(int x, int y) {
		setBounding(new Rectangle(x, y, getLook().getWidth(),
				getLook().getHeight()));
	}
	public BufferedImage getLook() {
		if (isNotCollected()) {
			try {
				look = ImageIO.read(getClass().getClassLoader()
						.getResourceAsStream("img/gold.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else
			look = null;

		return look;
	}
	public Rectangle getBounding() {
		return  bounding;
	}
	public Rectangle update(boolean scrollingLeft, boolean scrollingRight) {
		if (scrollingLeft)
			getBounding().x += 5;
		if (scrollingRight)
			getBounding().x -= 5;
		return getBounding();
	}
	public void setBounding(Rectangle bounding) {
		this.bounding = bounding;
	}
	public boolean isNotCollected() {
		return notCollected;
	}
	public void setNotCollected(boolean notCollected) {
		this.notCollected = notCollected;
	}

}
