package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Letters {

    private static char[] charLetterArray;

    private static void createLetterArray() {
        charLetterArray = new char[26];
        charLetterArray[0] = 'a';
        charLetterArray[1] = 'b';
        charLetterArray[2] = 'c';
        charLetterArray[3] = 'd';
        charLetterArray[4] = 'e';
        charLetterArray[5] = 'f';
        charLetterArray[6] = 'g';
        charLetterArray[7] = 'h';
        charLetterArray[8] = 'i';
        charLetterArray[9] = 'j';
        charLetterArray[10] = 'k';
        charLetterArray[11] = 'l';
        charLetterArray[12] = 'm';
        charLetterArray[13] = 'n';
        charLetterArray[14] = 'o';
        charLetterArray[15] = 'p';
        charLetterArray[16] = 'q';
        charLetterArray[17] = 'r';
        charLetterArray[18] = 's';
        charLetterArray[19] = 't';
        charLetterArray[20] = 'u';
        charLetterArray[21] = 'v';
        charLetterArray[22] = 'w';
        charLetterArray[23] = 'x';
        charLetterArray[24] = 'y';
        charLetterArray[25] = 'z';
    }

    public static BufferedImage[] createMessageArray(String messageString) {

        if (charLetterArray == null)
            createLetterArray();
        char[] messageStringArray = messageString.toCharArray();
        BufferedImage[] messagePicArray = new BufferedImage[messageStringArray.length];

        for (int i = 0; i < messagePicArray.length; i++) {

            for (char aCharLetterArray : charLetterArray) {

                if (messageStringArray[i] == (aCharLetterArray)) {

                    try {
                        messagePicArray[i] = ImageIO
                                .read(Letters.class.getResource("/resources/letters/" + aCharLetterArray + ".png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        return messagePicArray;
    }

}
