package game;

import java.awt.Rectangle;

public class Bounding extends Rectangle{
    
    //uses by cubes
    public Bounding(int x, int y, int width, int height){
    super(x, y, width, height);
    }
    //used by pic objects
    public Bounding(int x, int y){
    super(x, y);
    }
    
    public Bounding update(boolean scrollingLeft, boolean scrollingRight, int moveSpeed){
	
	if(scrollingLeft)
	    x = x + moveSpeed;
	
	if(scrollingRight)
	    x = x - moveSpeed;
	
	
	return this;
    }
    
    public Bounding update(boolean scrollingLeft, boolean scrollingRight, int moveSpeed, int playerMoveSpeed, boolean movingRight){
	
	if(scrollingLeft)
	    x = x + playerMoveSpeed;
	
	if(scrollingRight)
	    x = x - playerMoveSpeed;
	
	if(movingRight)
	    x = x + moveSpeed;
	x = x - moveSpeed;
	return this;
    }

}
