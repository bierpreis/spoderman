package graphics;

import game.Config;

import java.awt.image.BufferedImage;

public class Message {

    //todo: sticky message

    private final BufferedImage[] messagePicArray;
    private boolean isMessageFinal = false;

    private int timer;

    public Message(String messageString) {
        messagePicArray = Letters.createMessageArray(messageString);
        timer = Config.messageBaseTime + messagePicArray.length * Config.messageTimePerLetter;

    }

    public Message(String messageString, boolean finalMessageValue) {
        messagePicArray = Letters.createMessageArray(messageString);
        isMessageFinal = finalMessageValue;
        timer = Config.messageBaseTime + messagePicArray.length * Config.messageTimePerLetter;
    }

    public int getTimer() {
        return timer;
    }

    public BufferedImage[] getMessagePicArray() {
        return messagePicArray;
    }

    public void updateTimer() {
        if (!isMessageFinal)
            timer -= Config.msPerFrame;
    }

}
