package graphics;

import game.Config;

import java.awt.image.BufferedImage;

public class Message {
    private final BufferedImage[] messagePicArray;
    private boolean isMessageFinal = false;
    private int messageBaseTime = Integer.parseInt(Config.get("messageBaseTime"));
    private int messageTimePerLetter = Integer.parseInt(Config.get("messageTimePerLetter"));
    private int msPerFrame = Integer.parseInt(Config.get("msPerFrame"));

    private int timer;

    public Message(String messageString) {
        messagePicArray = Letters.createMessageArray(messageString);
        timer = messageBaseTime + messagePicArray.length * messageTimePerLetter;

    }

    public Message(String messageString, boolean finalMessageValue) {
        messagePicArray = Letters.createMessageArray(messageString);
        isMessageFinal = finalMessageValue;
        timer = messageBaseTime + messagePicArray.length * messageTimePerLetter;
    }

    public int getTimer() {
        return timer;
    }

    public BufferedImage[] getMessagePicArray() {
        return messagePicArray;
    }

    public void updateTimer() {
        if (!isMessageFinal)
            timer -= msPerFrame;
    }

}
