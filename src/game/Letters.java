package game;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Letters {

    static String[] buchstaben;
    static BufferedImage[] messagePicArray;

    static void createBuchstaben() {
	buchstaben = new String[26];
	buchstaben[0] = "a";
	buchstaben[1] = "b";
	buchstaben[2] = "c";
	buchstaben[3] = "d";
	buchstaben[4] = "e";
	buchstaben[5] = "f";
	buchstaben[6] = "g";
	buchstaben[7] = "h";
	buchstaben[8] = "i";
	buchstaben[9] = "j";
	buchstaben[10] = "k";
	buchstaben[11] = "l";
	buchstaben[12] = "m";
	buchstaben[13] = "n";
	buchstaben[14] = "o";
	buchstaben[15] = "p";
	buchstaben[16] = "q";
	buchstaben[17] = "r";
	buchstaben[18] = "s";
	buchstaben[19] = "t";
	buchstaben[20] = "u";
	buchstaben[21] = "v";
	buchstaben[22] = "w";
	buchstaben[23] = "x";
	buchstaben[24] = "y";
	buchstaben[25] = "z";
    }

    public static BufferedImage[] createMessageArray(String messageString) {

	if (buchstaben == null)
	    createBuchstaben();
	String[] messageStringArray = messageString.split("");
	BufferedImage[] messagePicArray = new BufferedImage[messageStringArray.length];

	for (int i = 0; i < messagePicArray.length; i++) {

	    for (int y = 0; y < buchstaben.length; y++) {

		if (messageStringArray[i].equals(buchstaben[y])) {

		    try {
			messagePicArray[i] = ImageIO
				.read(Letters.class.getResource("/letters/" + buchstaben[y] + ".png"));
		    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
		}
	    }

	}
	return messagePicArray;
    }

}
