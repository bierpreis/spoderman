package mainmenu;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import game.Config;
import graphics.Letters;

class Button extends Rectangle {

    private static boolean locked = true;
    private static int lockTimer = 0;
    private int msPerFrame = Integer.parseInt(Config.get("msPerFrame"));
    private int buttonLockTime = Integer.parseInt(Config.get("buttonLockTime"));

    private boolean focus = false;

    private final BufferedImage[] labelPicArray;

    Button(String label) {

        labelPicArray = Letters.createMessageArray(label);

        width = 400;
        height = 80;
        x = 100;

    }

    BufferedImage[] getPicArray() {
        return labelPicArray;
    }

    boolean getFocus() {
        return focus;
    }

    void setFocus() {
        focus = true;
    }

    void unSetFocus() {
        focus = false;
    }

    static boolean isLocked() {
        return locked;
    }

    static void setLock() {
        locked = true;
    }

    void update() {
        if (locked) {
            lockTimer += msPerFrame;
        }
        if (locked && lockTimer > buttonLockTime) {
            lockTimer = 0;
            locked = false;
        }
    }

}
