package game;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Enemy {
    private boolean movingRight = true;
    private boolean alive = true;

    private int moveSpeed = 1;

    private BufferedImage look;

    private Bounding bounding;
    private Bounding topBounding;

    Cube[] cube;

    // konstruktor
    public Enemy(int x, int y, String type, Cube[] cube) {

	look = createLook(type);

	bounding = new Bounding(x, y, look.getWidth(), look.getHeight());
	topBounding = new Bounding(x + 5, y, look.getWidth() - 10, 10);
	this.cube = cube;

    }

    // update
    public void update(boolean scrollingLeft, boolean scrollingRight) {

	// bewegung
	if (alive) {

	    if (movingRight) {
		bounding.x += moveSpeed;
		topBounding.x += moveSpeed;
	    }
	    if (!movingRight) {
		bounding.x -= moveSpeed;
		topBounding.x -= moveSpeed;
	    }

	    for (int i = 0; i < cube.length; i++) {
		if (bounding.intersects(cube[i].getBotBounding()) && movingRight) {
		    movingRight = false;

		}
		if (bounding.intersects(cube[i].getLeftBounding()) && movingRight) {
		    movingRight = false;

		}
		if (bounding.intersects(cube[i].getRightBounding()) && !movingRight) {
		    movingRight = true;

		}
	    }
	}

	// scrolling
	bounding = bounding.scroll(scrollingLeft, scrollingRight);
	// Bounding updaten

	topBounding = topBounding.scroll(scrollingLeft, scrollingRight);
	
	

    }

    public void kill() {
	try {
	    look = ImageIO.read(getClass().getClassLoader().getResourceAsStream("img/blood.png"));
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	alive = false;
    }

    public BufferedImage getLook() {

	return look;
    }

    public BufferedImage createLook(String type) {
	if (type == "dolan") {

	    try {
		look = ImageIO.read(getClass().getClassLoader().getResourceAsStream("img/Dolan.png"));
	    }

	    catch (IOException e) {
		e.printStackTrace();
	    }
	}

	if (type == "gooby") {

	    try {
		look = ImageIO.read(getClass().getClassLoader().getResourceAsStream("img/gooby.png"));
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}

	return look;
    }

    public Rectangle getTopBounding() {
	return topBounding;
    }

    public boolean getAlive() {
	return alive;
    }

    public Bounding getBounding() {
	return bounding;
    }

}
