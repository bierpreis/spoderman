package game;

import java.awt.Rectangle;

public class Cube extends Bounding {

	
	Bounding topBounding;
	Bounding botBounding;
	Bounding leftBounding;
	Bounding rightBounding;

	public Cube(int x, int y, int width, int height) {
		super(x, y, width, height);
		
		topBounding = new Bounding(x, y, width, 15);;
		botBounding = new Bounding(x, y + 15, 5, height - 15);;
		leftBounding = new Bounding(x + width - 5, y + 15, 5, height - 15);
		rightBounding = new Bounding(x + 5, y + height - 3, width - 10, 5);
	}

	public void updateBounding(boolean scrollingLeft, boolean scrollingRight, int moveSpeed) {
	    
		this.update(scrollingLeft, scrollingRight);
		topBounding = topBounding.update(scrollingLeft, scrollingRight);
		leftBounding = leftBounding.update(scrollingLeft, scrollingRight);
		rightBounding = rightBounding.update(scrollingLeft, scrollingRight);
		botBounding = botBounding.update(scrollingLeft, scrollingRight);
	}

	public Rectangle getTopBounding() {
		return topBounding;
	}
	public Rectangle getLeftBounding() {
		return leftBounding;
	}
	public Rectangle getRightBounding() {
		return rightBounding;
	}
	public Rectangle getBotBounding() {
		return botBounding;
	}

}
