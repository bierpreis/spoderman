package menu;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import general.Letters;

public class Button extends Rectangle {

    static boolean locked = true;
    static int lockTime = 550;
    static int lockTimer = 0;

    boolean focus = false;

    BufferedImage[] labelPicArray;

    public Button(String label) {

	labelPicArray = Letters.createMessageArray(label);

	width = 400;
	height = 80;
	x = 100;

    }

    public BufferedImage[] getPicArray() {
	return labelPicArray;
    }

    public boolean getFocus() {
	return focus;
    }

    public void setFocus() {
	focus = true;
    }

    public void unSetFocus() {
	focus = false;
    }

    public static boolean getLocked() {
	return locked;
    }

    static void setLock() {
	locked = true;
    }

    public void update() {
	if (locked) {
	    lockTimer += 15;
	}
	if (locked && lockTimer > lockTime) {
	    lockTimer = 0;
	    locked = false;
	}
    }

}
