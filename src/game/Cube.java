package game;

import java.awt.Rectangle;

public class Cube extends Rectangle {

	Rectangle bounding;
	Rectangle topBounding;
	Rectangle botBounding;
	Rectangle leftBounding;
	Rectangle rightBounding;

	public Cube(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void updateBounding() {
		bounding = new Rectangle(x, y + 10, width, height - (10));
		topBounding = new Rectangle(x, y, width, 15);
		leftBounding = new Rectangle(x, y + 15, 5, height - 15);
		rightBounding = new Rectangle(x + width - 5, y + 15, 5, height - 15);
		botBounding = new Rectangle(x + 5, y + height - 3, width - 10, 5);
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
