package general;

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
	if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
	    keyEscape = true;
	
	if (e.getKeyCode() == KeyEvent.VK_ENTER)
	    keyEnterIsPressed = true;
	if (e.getKeyCode() == KeyEvent.VK_LEFT)
	    keyLeft = true;
	if (e.getKeyCode() == KeyEvent.VK_RIGHT)
	    keyRight = true;
	if (e.getKeyCode() == KeyEvent.VK_UP)
	    keyUp = true;
	if (e.getKeyCode() == KeyEvent.VK_DOWN)
	    keyDown = true;
	if (e.getKeyCode() == KeyEvent.VK_SPACE)
	    keySpace = true;

    }

    @Override
    public void keyReleased(KeyEvent e) {
	if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
	    keyEscape = false;
	if (e.getKeyCode() == KeyEvent.VK_ENTER)
	    keyEnterIsPressed = false;
	if (e.getKeyCode() == KeyEvent.VK_LEFT)
	    keyLeft = false;
	if (e.getKeyCode() == KeyEvent.VK_RIGHT)
	    keyRight = false;
	if (e.getKeyCode() == KeyEvent.VK_SPACE)
	    keySpace = false;
	if (e.getKeyCode() == KeyEvent.VK_UP)
	    keyUp = false;
	if (e.getKeyCode() == KeyEvent.VK_DOWN)
	    keyDown = false;
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