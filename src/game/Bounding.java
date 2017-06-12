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
    
    public Bounding update(boolean scrollingLeft, boolean scrollingRight){
	
	if(scrollingLeft)
	    x = x + Config.getPlayerMoveSpeed();
	
	if(scrollingRight)
	    x = x - Config.getPlayerMoveSpeed();
	
	
	return this;
    }
    
    public Bounding update(boolean scrollingLeft, boolean scrollingRight, boolean movingRight){
	
	if(scrollingLeft)
	    x = x + Config.getPlayerMoveSpeed();
	
	if(scrollingRight)
	    x = x - Config.getPlayerMoveSpeed();
	
	if(movingRight)
	    x = x + Config.getPlayerMoveSpeed();
	x = x - Config.getPlayerMoveSpeed();
	return this;
    }

}
