package menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

import game.Config;
import game.Frame;
import game.Lvl;
import game.Player;

public class Menu extends JFrame {

    boolean up, down, enter;
    boolean codeinputLock = false;
    boolean menuLock = false;
    int lvlNumber = 0;

    // int screenX, screenY;
    Button[] buttonArray;
    Menu menu;

    int screenX, screenY;

    private Screen screen;

    public Menu(int screenX, int screenY) {

	super("welcum to spodermens advenshur");

	this.screenX = screenX;
	this.screenY = screenY;
	screen = new Screen();

	screen.setBounds(100, 100, screenX, screenY);

	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setSize(screenX, screenY);
	setVisible(true);
	setResizable(false);

	buttonArray = new Button[3];

	buttonArray[0] = new Button("new gaem");
	buttonArray[1] = new Button("lvl coed");
	buttonArray[2] = new Button("leaf");

	add(screen);

	addKeyListener(new KeyHandler());
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

    public class KeyHandler implements KeyListener {

	@Override

	public void keyPressed(KeyEvent e) {
	    if (e.getKeyCode() == KeyEvent.VK_ENTER)
		enter = true;
	    if (e.getKeyCode() == KeyEvent.VK_UP)
		up = true;
	    if (e.getKeyCode() == KeyEvent.VK_DOWN)
		down = true;

	}

	@Override
	public void keyReleased(KeyEvent e) {
	    if (e.getKeyCode() == KeyEvent.VK_ENTER)
		enter = false;
	    if (e.getKeyCode() == KeyEvent.VK_UP)
		up = false;
	    if (e.getKeyCode() == KeyEvent.VK_DOWN)
		down = false;
	}

	// Unnötig
	@Override
	public void keyTyped(KeyEvent e) {
	}
    }

    boolean update() {
	updateFocus();
	Boolean escape = doButtonActions();

	return escape;
    }

    void updateFocus() {
	for (int i = 0; i < buttonArray.length; i++) {
	    buttonArray[i].update();

	    // pfeil runter
	    if (buttonArray[i].getFocus() && down && !Button.getLocked()) {
		Button.setLock();
		if (i < buttonArray.length - 1) {
		    buttonArray[i + 1].setFocus();
		    buttonArray[i].unSetFocus();
		}
	    }

	    // pfeil hoch
	    if (buttonArray[i].getFocus() && up && !Button.getLocked()) {
		if (i > 0) {
		    buttonArray[i - 1].setFocus();
		    Button.setLock();
		    buttonArray[i].unSetFocus();
		}
	    }
	}
    }

    boolean doButtonActions() {
	if (buttonArray[0].getFocus() && enter) {
	    enter = false;
	    dispose();
	    game(1);

	}
	if (buttonArray[1].getFocus() && enter) {
	    enter = false;
	    new CodeInputWindow(this);
	    if (lvlNumber != 0)
		game(lvlNumber);

	}
	if (buttonArray[2].getFocus() && enter) {
	    dispose();
	    System.exit(0);
	}
	return false;

    }

    public void showMenu() {

	boolean escape = false;

	while (!escape) {
	    try {
		Thread.sleep(15);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	    repaintScreen();
	    escape = update();

	}
	

    }

    public void game(int lvlNumber) {

	int targetFps = Config.getTargetFps();;

	int msPerFrame = 1000 / targetFps;

	this.lvlNumber = lvlNumber;
	Lvl lvl = new Lvl(lvlNumber);
	Player player = new Player(lvl);
	Frame f = new Frame(player, lvl, screenX, screenY);
	boolean running = true;
	while (running) {
	    long startTime = System.currentTimeMillis();

	    checkLvlUp(player);
	    player.update(f.getLeft(), f.getRight(), f.getJump());
	    f.repaintScreen();
	    if (f.getLastFrame())
		running = false;

	    long sleepTime = msPerFrame - (System.currentTimeMillis() - startTime);
	    if (sleepTime > 0)
		try {
		    Thread.sleep(sleepTime);

		} catch (InterruptedException e) {
		    e.printStackTrace();
		}

	}

	Menu menu = new Menu(screenX, screenY);
	menu.showMenu();
	System.out.println("end of menu while(running)");

    }

    void checkLvlUp(Player player) {
	if (player.getLvlUp()) {
	    lvlNumber += 1;
	    player.setLvlUp(false);
	    game(lvlNumber);
	    dispose();
	    // useless comment
	}
    }

    void setLvl(int lvl) {
	this.lvlNumber = lvl;
	dispose();
    }

    void unSetMenuLock() {
	menuLock = false;
    }

}
