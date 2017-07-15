package menu;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;

import game.Config;
import game.Frame;
import game.KeyHandler;
import game.Lvl;
import game.Player;

public class Menu extends JFrame {

    boolean codeinputLock = false;
    boolean menuLock = false;
    int lvlNumber = 0;

    // int screenX, screenY;
    Button[] buttonArray;

    private Screen screen;

    KeyHandler keyHandler = new KeyHandler();

    public Menu() {

	super("welcum to spodermens advenshur");

	screen = new Screen();

	screen.setBounds(100, 100, Config.getScreenX(), Config.getScreenY());

	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setSize(Config.getScreenX(), Config.getScreenY());
	setVisible(true);
	setResizable(false);

	buttonArray = new Button[3];

	buttonArray[0] = new Button("new gaem");
	buttonArray[1] = new Button("lvl coed");
	buttonArray[2] = new Button("leaf");

	add(screen);

	addKeyListener(keyHandler);
	buttonArray[0].setFocus();

    }

    private class Screen extends JLabel {
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    draw(g);
	}
    }

    private void draw(Graphics g) {
	int buttonY = 100;
	g.setColor(Color.BLUE);

	drawButton(buttonArray[0], g, buttonY);
	drawButton(buttonArray[1], g, buttonY + 100);
	drawButton(buttonArray[2], g, buttonY + 200);
    }

    // button zeichnen
    void drawButton(Button button, Graphics g, int y) {

	if (button.getFocus()) {
	    g.setColor(Color.RED);
	} else
	    g.setColor(Color.BLUE);

	g.fillRect((int) button.getX(), y, (int) button.getWidth(), (int) button.getHeight());

	int letterX[] = new int[button.getPicArray().length];

	letterX[0] = (int) button.getX();

	for (int j = 0; j < button.getPicArray().length; j++) {
	    g.drawImage(button.getPicArray()[j], letterX[j], y, null);
	    if (j < button.getPicArray().length - 1) {
		letterX[j + 1] = letterX[j] + 40;
	    }
	}
    }

    // screen zeichnen
    void repaintScreen() {
	screen.repaint();
    }

    public String update() {
	updateFocus();
	return getButtonActions();

    }

    void updateFocus() {
	for (int i = 0; i < buttonArray.length; i++) {
	    buttonArray[i].update();

	    // pfeil runter
	    if (buttonArray[i].getFocus() && keyHandler.getDown() && !Button.getLocked()) {
		Button.setLock();
		if (i < buttonArray.length - 1) {
		    buttonArray[i + 1].setFocus();
		    buttonArray[i].unSetFocus();
		}
	    }

	    // pfeil hoch
	    if (buttonArray[i].getFocus() && keyHandler.getUp() && !Button.getLocked()) {
		if (i > 0) {
		    buttonArray[i - 1].setFocus();
		    Button.setLock();
		    buttonArray[i].unSetFocus();
		}
	    }
	}
    }

    String getButtonActions() {
	if (buttonArray[0].getFocus() && keyHandler.getEnter()) {
	    return "NEW_GAME";
	}
	if (buttonArray[1].getFocus() && keyHandler.getEnter()) {
	    new CodeInputWindow(this);
	    if (lvlNumber != 0)
		game(lvlNumber);

	}
	if (buttonArray[2].getFocus() && keyHandler.getEnter()) {
	    // dispose();

	    return "EXIT";
	}
	return "CONTINUE";

    }

    public boolean showMenu() {

	while (true) {
	    
	    repaintScreen();

	    switch (update()) {
	    case "CONTINUE":
		break;
	    case "NEW_GAME":
		game(1);
		return false;
	    case "EXIT":
		return true;
	    default:
		return false;
	    }
	    
	    try {
		Thread.sleep(15);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }

	}

    }

    public String game(int lvlNumber) {
	int nsPerFrame = 1000000000 / Config.getTargetFps();

	this.lvlNumber = lvlNumber;
	Lvl lvl = new Lvl(lvlNumber);
	Player player = new Player(lvl);
	Frame f = new Frame(player, lvl);

	// returnvalues:
	// 0 is contuinue game loop
	// -1 is end game and go back to menu
	// 1 is lvl up
	String nextAction = "CONTINUE";
	while (nextAction.equals("CONTINUE")) {

	    long startTime = System.nanoTime();
	    nextAction = player.update(f.getKeyHandler());
	    f.repaintScreen();
	    if (!f.isDisplayable())
		nextAction = "EXIT";

	    int sleepTime = (int) (nsPerFrame - (System.nanoTime() - startTime)) / 1000000;
	    if (sleepTime > 0)
		try {
		    Thread.sleep(sleepTime);

		} catch (InterruptedException e) {
		    e.printStackTrace();
		}

	}
	f.dispose();
	// sleep to avoid to start same lvl again with enter press
	try {
	    Thread.sleep(600);
	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	return nextAction;
    }

    void setLvl(int lvl) {
	this.lvlNumber = lvl;
	dispose();
    }

    void unSetMenuLock() {
	menuLock = false;
    }

}
