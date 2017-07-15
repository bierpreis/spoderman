package menu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import game.Game;

public class CodeInputWindow extends JDialog implements ActionListener {

    boolean enterKeyPressed = false;

    int lvl = 0;
    int screenX, screenY;
    JDialog input;
    JButton buttonOK;
    JTextField textInput;
    Menu menu;
    String code;

    CodeInputWindow(Menu menu) {
	this.menu = menu;

	setLayout(new BorderLayout());

	input = new JDialog();

	input.setTitle("lvl coed");
	input.setSize(300, 150);
	input.setLocation(250, 250);

	JPanel panel = new JPanel();

	JLabel label = new JLabel("giff coed");
	panel.add(label);

	textInput = new InputField(15);
	textInput.setBackground(Color.YELLOW);
	panel.add(textInput);

	buttonOK = new JButton("  k  ");
	panel.add(buttonOK);
	buttonOK.addActionListener(new ButtonListener());

	buttonOK.setVisible(true);

	input.setModal(true);
	input.add(panel);
	input.setVisible(true);

	buttonOK.addKeyListener(new KeyHandler());

	addKeyListener(new KeyHandler());
	setFocusable(true);
    }

    void showCodeInput() {

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
	if (ae.getSource() == buttonOK) {
	    if (textInput.getText().equals("lol")) {
		input.dispose();
		new Game(2);
		
	    } else {
		input.dispose();
		wrongCode();
	    }
	}
    }

    private void wrongCode() {
	WrongCodeWindow wrongCode = new WrongCodeWindow();
	wrongCode.showWrongCode();
	dispose();
    }

    private class KeyHandler implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {

	    if (e.getKeyCode() == KeyEvent.VK_ENTER)
		System.out.println("Enter was pressed");
	    enterKeyPressed = true;

	}

	@Override
	public void keyReleased(KeyEvent e) {
	    if (e.getKeyCode() == KeyEvent.VK_ENTER)
		enterKeyPressed = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

    }

    class ButtonListener implements ActionListener {
	@Override

	public void actionPerformed(ActionEvent e) {
	    code = textInput.getText();
	    checkLvlCode(code);
	}
    }

    void checkLvlCode(String code) {

	if (code != null)
	    if (code.equals("lol")) {
//		menu.update(2);
		menu.dispose();
	    } else {
		wrongCode();
		input.dispose();
	    }

    }

}
