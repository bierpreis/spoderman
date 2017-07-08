package game;

import java.awt.image.BufferedImage;

public class Message {
    
    private BufferedImage[] messagePicArray;
    
    private boolean lock = false;
    private int timer;
    
    public Message(String messageString){
	messagePicArray = Letters.createMessageArray(messageString);
	timer = Config.getMessageBaseTime() + messagePicArray.length * Config.getMessageTimePerLetter();
	
    }
    
    public int getTimer(){
	return timer;
    }
    
    public boolean getLock(){
	return lock;
    }
    
    public void setLock(){
	lock = true;
    }
    
    public BufferedImage[] getMessagePicArray(){
	return messagePicArray;
    }
    
    public void updateTimer(int elapsedTime){
	timer -= elapsedTime;

	
	
    }

}
