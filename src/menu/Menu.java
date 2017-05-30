package menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

import game.Frame;
import game.Map;
import game.Player;

public class Menu extends JFrame {

	boolean up, down, enter;
	boolean codeinputLock = false;
	boolean menuLock = false;
	int lvl = 0;

	int screenX, screenY;
	Button[] buttonArray;
	Menu menu;

	boolean firstDraw = true;

	private Screen screen;
	//private CodeInput codeInput;
	CodeInputWindow codeInput;

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
		if (firstDraw) {
			buttonArray[0].setFocus();
			firstDraw = false;
		}
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

	void update() {
		updateFocus();
		doButtonActions();
		if (enter)
			enter = false; // sonst bleibt es ewig true
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

	void doButtonActions() {
		if (buttonArray[0].getFocus() && enter) {
			dispose();
			game(1);
		}
		if (buttonArray[1].getFocus() && enter) {
			codeInput = new CodeInputWindow(this);
			if (lvl != 0)
				game(lvl);
			
		}
		if (buttonArray[2].getFocus() && enter) {
			dispose();
			System.exit(0);
		}

	}

	public void showMenu() {
		if (menu == null)
			menu = new Menu(screenX, screenY);
		while (true) {
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			menu.repaintScreen();
			menu.update();
		}
	}

	public void game(int lvl) {
		
		this.lvl = lvl;
		Map map = new Map(lvl);
		Player player = new Player(screenX, map);
		Frame f = new Frame(player, map, screenX, screenY);
		
		
		long nextSecond = System.currentTimeMillis() +1000;
		int fpsCounter = 0;

		while (true) {
		    	fpsCounter+=1;
		    	if(System.currentTimeMillis()>nextSecond){
		    	    nextSecond = System.currentTimeMillis()+1000;
		    	    System.out.println(fpsCounter);
		    	    fpsCounter = 0;
		    	}
			checkLvlUp(player);
			player.update(f.getLeft(), f.getRight(), f.getJump());
			f.repaintScreen();

			if (f.getEndFrame()) // geht ni!!!
				showMenu();
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	void checkLvlUp(Player player) {
		if (player.getLvlUp()) {
			lvl += 1;
			player.setLvlUp(false);
			game(lvl);
			dispose();
			//useless comment
		}
	}

	void setLvl(int lvl) {
		this.lvl = lvl;
		dispose();
	}

	void unSetMenuLock() {
		menuLock = false;
	}

}
