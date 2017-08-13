package general;

import java.awt.image.BufferedImage;

public class Message {

    private final BufferedImage[] messagePicArray;

    private int timer;

    public Message(String messageString) {
        messagePicArray = Letters.createMessageArray(messageString);
        timer = Config.messageBaseTime + messagePicArray.length * Config.messageTimePerLetter;

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
