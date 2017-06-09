package game;

import java.awt.Rectangle;

public class Bounding extends Rectangle{
    
    public Bounding(int x, int y, int width, int height){
    super(x, y, width, height);
    }
    
    public Bounding update(boolean movingLeft, boolean movingRight, int moveSpeed){
	
	if(movingLeft)
	    x = x + moveSpeed;
	
	if(movingRight)
	    x = x - moveSpeed;
	
	
	return this;
    }

}
