package game;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Enemy extends Bounding {
    private boolean movingRight = true;
    private boolean alive = true;

    private int moveSpeed = 1;

    private BufferedImage look;

    private Bounding topBounding;

    Cube[] cube;

    // konstruktor
    public Enemy(int x, int y, String type, Cube[] cube) {
	super(x, y);
	look = createLook(type);
	this.x = x;
	this.y = y;
	this.width = look.getWidth();
	this.height = look.getHeight();
	this.cube = cube;

	topBounding = new Bounding(x + 5, y, width - 10, 10);
    }

    // update
    public void update(boolean scrollingLeft, boolean scrollingRight) {

	// bewegung
	if (alive) {
	    if (movingRight){
		x += moveSpeed;
	    	topBounding.x+=moveSpeed;
	    }
	    if (!movingRight){
		x -= moveSpeed;
		topBounding.x-=moveSpeed;}
	    
	}
	for (int i = 0; i < cube.length; i++) {
	    if (intersects(cube[i]) && movingRight) {
		movingRight = false;

	    }
	    if (intersects(cube[i].getLeftBounding()) && movingRight) {
		movingRight = false;

	    }
	    if (intersects(cube[i].getRightBounding()) && !movingRight) {
		movingRight = true;

	    }

	}

	// scrolling
	update(scrollingLeft, scrollingRight, 5);
	// Bounding updaten

	if (alive)
	    topBounding = topBounding.update(scrollingLeft, scrollingRight, 5);
	System.out.println(topBounding.x);

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


}
