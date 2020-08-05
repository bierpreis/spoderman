package mainmenu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

class InputField extends JTextField implements KeyListener {

    InputField(int cols) {
        super(cols);

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //code= getText();
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            System.out.println("Es war Enter");  //todo wtf what is this?

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

}
