package game;



public class Cube{

	Bounding bounding;
	Bounding topBounding;
	Bounding botBounding;
	Bounding leftBounding;
	Bounding rightBounding;

	public Cube(int x, int y, int width, int height) {
		
		bounding = new Bounding(x,y,width, height);
		topBounding = new Bounding(x, y, width, 15);;
		botBounding = new Bounding(x, y + 15, width, height - 15);;
		leftBounding = new Bounding(x + width - 5, y + 15, 5, height - 15);
		rightBounding = new Bounding(x + 5, y + height - 3, width - 10, 5);
	}

	public void updateBounding(boolean scrollingLeft, boolean scrollingRight, int moveSpeed) {
	    
		bounding.update(scrollingLeft, scrollingRight);
		topBounding = topBounding.update(scrollingLeft, scrollingRight);
		leftBounding = leftBounding.update(scrollingLeft, scrollingRight);
		rightBounding = rightBounding.update(scrollingLeft, scrollingRight);
		botBounding = botBounding.update(scrollingLeft, scrollingRight);
	}
	public Bounding getBounding(){
	    return bounding;
	}
	public Bounding getTopBounding() {
		return topBounding;
	}
	public Bounding getLeftBounding() {
		return leftBounding;
	}
	public Bounding getRightBounding() {
		return rightBounding;
	}
	public Bounding getBotBounding() {
		return botBounding;
	}

}
