package general;

import java.awt.image.BufferedImage;

public class Message {

    private final BufferedImage[] messagePicArray;

    private int timer;

    public Message(String messageString) {
	messagePicArray = Letters.createMessageArray(messageString);
	timer = Config.getMessageBaseTime() + messagePicArray.length * Config.getMessageTimePerLetter();

    }

    public int getTimer() {
	return timer;
    }

    public BufferedImage[] getMessagePicArray() {
	return messagePicArray;
    }

    public void updateTimer(int elapsedTime) {
	timer -= elapsedTime;

    }

}
