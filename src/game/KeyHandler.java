package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    private boolean keyLeft = false;
    private boolean keyRight = false;
    private boolean keyUp = false;
    private boolean keyDown = false;

    private boolean keySpace = false;
    private boolean keyEscape = false;
    private boolean keyEnterIsPressed = false;

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case (KeyEvent.VK_ESCAPE):
                keyEscape = true;
                break;
            case (KeyEvent.VK_ENTER):
                keyEnterIsPressed = true;
                break;
            case (KeyEvent.VK_LEFT):
                keyLeft = true;
                break;
            case (KeyEvent.VK_RIGHT):
                keyRight = true;
                break;
            case (KeyEvent.VK_UP):
                keyUp = true;
                break;
            case (KeyEvent.VK_DOWN):
                keyDown = true;
                break;
            case (KeyEvent.VK_SPACE):
                keySpace = true;
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        switch (e.getKeyCode()) {
            case (KeyEvent.VK_ESCAPE):
                keyEscape = false;
                break;
            case (KeyEvent.VK_ENTER):
                keyEnterIsPressed = false;
                break;
            case (KeyEvent.VK_LEFT):
                keyLeft = false;
                break;
            case (KeyEvent.VK_RIGHT):
                keyRight = false;
                break;
            case (KeyEvent.VK_UP):
                keyUp = false;
                break;
            case (KeyEvent.VK_DOWN):
                keyDown = false;
                break;
            case (KeyEvent.VK_SPACE):
                keySpace = false;
                break;
        }

    }

    // Unnötig
    @Override
    public void keyTyped(KeyEvent e) {
    }

    public boolean getSpace() {
        return keySpace;
    }

    public boolean getEnter() {
        if (keyEnterIsPressed) {
            keyEnterIsPressed = false;
            return true;
        }

        return false;
    }

    public boolean getEscape() {

        return keyEscape;
    }

    public boolean getLeft() {
        return keyLeft;

    }

    public boolean getRight() {
        return keyRight;
    }

    public boolean getUp() {
        return keyUp;
    }

    public boolean getDown() {
        return keyDown;
    }
}