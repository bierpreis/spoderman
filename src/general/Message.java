package general;

import java.awt.image.BufferedImage;

public class Message {

    private BufferedImage[] messagePicArray;

    private boolean escLock = false;
    private int timer;

    public Message(String messageString) {
	messagePicArray = Letters.createMessageArray(messageString);
	timer = Config.getMessageBaseTime() + messagePicArray.length * Config.getMessageTimePerLetter();

    }

    public int getTimer() {
	return timer;
    }

    public boolean getEscLock() {
	return escLock;
    }

    public void setEscLock(boolean lock) {
	escLock = lock;
    }

    public BufferedImage[] getMessagePicArray() {
	return messagePicArray;
    }

    public void updateTimer(int elapsedTime) {
	timer -= elapsedTime;

    }

}
