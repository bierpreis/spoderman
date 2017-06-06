package game;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Enemy {
    private boolean movingRight = true;
    private boolean alive = true;
    private boolean scrollingRight = false;
    private boolean scrollingLeft = false;

    private int moveSpeed = 1;
    private int x;
    private int y;
    private int xSize;
    private int ySize;

    private String type = "dolan";

    private BufferedImage look;
    private Rectangle bounding;
    private Rectangle topBounding;

    Cube[] cube;

    // konstruktor
    public Enemy(int x, int y, String type, Cube[] cube) {
	this.setX(x);
	this.y = y;
	this.xSize = getLook().getWidth();
	this.ySize = getLook().getHeight();
	this.type = type;
	this.cube = cube;
	bounding = new Rectangle(x, y, xSize, ySize);
	topBounding = new Rectangle(x + 5, y, xSize - 10, 10);
    }

    // update
    public void update(boolean scrollingLeft, boolean scrollingRight) {

	// bewegung
	if (alive) {
	    if (movingRight)
		bounding.x += moveSpeed;
	    if (!movingRight)
		bounding.x -= moveSpeed;
	}
	for (int i = 0; i < cube.length; i++) {
	    if (bounding.intersects(cube[i]) && movingRight) {
		movingRight = false;

	    }
	    if (bounding.intersects(cube[i].getLeftBounding()) && movingRight) {
		movingRight = false;

	    }
	    if (bounding.intersects(cube[i].getRightBounding()) && !movingRight) {
		movingRight = true;

	    }

	}

	// scrolling
	if (scrollingLeft)
	    bounding.x += 5; // hier muss player.movespeed irgendwie hin
	if (scrollingRight)
	    bounding.x -= 5; // genauso

	// Bounding updaten

	if (alive)
	    topBounding = new Rectangle(bounding.x + 5, y, bounding.width - 8, 10);

    }

    public void kill() {
	alive = false;
    }

    public BufferedImage getLook() {
	if (type == "dolan") {
	    if (alive)
		try {
		    look = ImageIO.read(getClass().getClassLoader().getResourceAsStream("img/Dolan.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    else
		try {
		    look = ImageIO.read(getClass().getClassLoader().getResourceAsStream("img/blood.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	if (type == "gooby") {
	    if (alive)
		try {
		    look = ImageIO.read(getClass().getClassLoader().getResourceAsStream("img/gooby.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    else
		try {
		    look = ImageIO.read(getClass().getClassLoader().getResourceAsStream("img/blood.png"));
		} catch (IOException e) {
		    e.printStackTrace();

		}
	}

	return look;
    }

    public Rectangle getBounding() {
	return bounding;
    }

    public Rectangle getTopBounding() {
	return topBounding;
    }

    public boolean getAlive() {
	return alive;
    }

    public boolean isScrollingLeft() {
	return scrollingLeft;
    }

    public void setScrollingLeft(boolean scrollingLeft) {
	this.scrollingLeft = scrollingLeft;
    }

    public boolean isScrollingRight() {
	return scrollingRight;
    }

    public void setScrollingRight(boolean scrollingRight) {
	this.scrollingRight = scrollingRight;
    }

    public int getX() {
	return x;
    }

    public void setX(int x) {
	this.x = x;
    }
}
