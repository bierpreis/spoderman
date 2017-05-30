package game;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;


import javax.imageio.ImageIO;


public class Bigmek extends Rectangle{

	/**
	 * id weil sonst warning kommt
	 */

    //erster kommentar
    //second comment

	private static final long serialVersionUID = 1L;
	private boolean notCollected = true;
	private BufferedImage look;
	private Rectangle bounding ; 
	
public Bigmek(int x, int y){
	this.x = x;
	this.y = y;
	setBounding(new Rectangle(x, y, getLook().getWidth(), getLook().getHeight()));
	
}
public BufferedImage getLook(){
			if(isNotCollected() == true){
				try {
					look = ImageIO.read(getClass().getClassLoader().getResourceAsStream("img/bigmek.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else look = null;
		
	return look;
	}

public Rectangle update(boolean scrollingLeft, boolean scrollingRight){
	if(scrollingLeft)getBounding().x+=5;
	if(scrollingRight)getBounding().x-=5;
	return getBounding();
	}
public Rectangle getBounding() {
	return bounding;
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

// useless comment
}

