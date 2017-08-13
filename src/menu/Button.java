package menu;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import general.Config;
import general.Letters;

class Button extends Rectangle {

    private static boolean locked = true;
    private static int lockTimer = 0;

    private boolean focus = false;

    private final BufferedImage[] labelPicArray;

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

    public static boolean isLocked() {
        return locked;
    }

    static void setLock() {
        locked = true;
    }

    public void update() {
        if (locked) {
            lockTimer += Config.msPerFrame;
        }
        if (locked && lockTimer > Config.buttonLockTime) {
            lockTimer = 0;
            locked = false;
        }
    }

}
