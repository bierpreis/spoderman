package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Frame extends JFrame {

    private boolean key_left = false;
    private boolean key_right = false;
    private boolean key_jump = false;
    private boolean escape = false;
    private boolean enter;
    private boolean lastFrame = false;

    private boolean escaping;

    int escapeTime = 0; // esc dialog
    private int fps = 0;
    private int fpsCounter = 0;
    private long timeUntilLastSecond = System.currentTimeMillis() + 500;

    private Screen screen;
    private final Player player;
    private Lvl lvl;

    public Frame(Player player, Lvl lvl, int screenX, int screenY) {
	super("spodermens advenshur");

	screen = new Screen();
	screen.setBounds(100, 0, screenX, screenY);
	add(screen);
	addKeyListener(new KeyHandler());

	this.player = player;
	this.lvl = lvl;

	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setSize(screenX, screenY);

	setResizable(false);
	setVisible(true);

    }

    // steuerung
    public boolean getLeft() {
	return key_left;
    }

    public boolean getRight() {
	return key_right;
    }

    public boolean getJump() {
	return key_jump;
    }

    // screen zeichnen
    public void repaintScreen() {
	screen.repaint();

    }

    // Fenster
    private class Screen extends JLabel {
	@Override
	protected void paintComponent(Graphics g) {

	    super.paintComponent(g);

	    g.setColor(Color.BLUE);
	    for (int i = 0; i < lvl.getCubes().length; i++) {

		g.fillRect((int) lvl.getCubes()[i].getBounding().getX(), (int) lvl.getCubes()[i].getBounding().getY(),
			(int) lvl.getCubes()[i].getBounding().getWidth(), (int) lvl.getCubes()[i].getBounding().getHeight());
	    }
	    g.setColor(Color.BLACK);

	    draw(g);
	}
    }

    // zeichnen
    private void draw(Graphics g) {

	// message anzeigen
	sayMessage(player.isSayMessage(), g);
	drawUnits(g);
	calcFps();

	g.drawString("Sweg Count:" + player.getSwegCount(), 25, 25);
	g.drawString("Fagits rekt:" + player.getKills(), 150, 25);
	g.drawString("Sp0der Lyfez :" + player.getLifes(), 300, 25);
	g.drawString("FPS: " + fps, 600, 25);

	// macht den escape dialog
	if (escape) {
	    sayEscape();
	}
	if (escaping)
	    escapeTime += 15;
	if (escaping && escape && escapeTime > 600) {
	    lastFrame = true;
	    dispose();
	}
	if (escaping && enter) {
	    escaping = false;
	    player.unlockMessage();
	    escapeTime = 0;
	}

    }

    private void calcFps() {
	fpsCounter++;
	if (System.currentTimeMillis() > timeUntilLastSecond) {
	    timeUntilLastSecond = System.currentTimeMillis() + 500;
	    fps = 2*fpsCounter;
	    fpsCounter = 0;
	}

    }

    void sayMessage(boolean isSayMessage, Graphics g) {
	if (isSayMessage) {
	    int letterX[] = new int[player.getMessagePics().length];
	    int letterY[] = new int[player.getMessagePics().length];
	    letterX[0] = 0;
	    letterY[0] = 40;
	    for (int i = 0; i < player.getMessagePics().length; i++) {
		g.drawImage(player.getMessagePics()[i], letterX[i], letterY[i], null);
		if (i < player.getMessagePics().length - 1) {
		    if (letterX[i] > 600) {
			letterX[i + 1] = 40;
			letterY[i + 1] = letterY[i] + 80;
		    } else {
			letterX[i + 1] = letterX[i] + 40;
			letterY[i + 1] = letterY[i];
		    }
		}
	    }
	}

    }

    void drawUnits(Graphics g) {
	g.drawImage(player.getLook(), player.getBounding().x, player.getBounding().y, null);
	if (lvl.getSweg() != null)
	    for (int i = 0; i < lvl.getSweg().length; i++) {
		g.drawImage(lvl.getSweg()[i].getLook(), lvl.getSweg()[i].getBounding().x,
			lvl.getSweg()[i].getBounding().y, null);
	    }
	if (lvl.getBigmek() != null) {
	    g.drawImage(lvl.getBigmek().getLook(), (int) lvl.getBigmek().getBounding().getX(), (int) lvl.getBigmek().getBounding().getY(), null);
	}
	if (lvl.getEnemy() != null)
	    for (int i = 0; i < lvl.getEnemy().length; i++) {
		g.drawImage(lvl.getEnemy()[i].getLook(), (int)lvl.getEnemy()[i].getX(),
			(int)lvl.getEnemy()[i].getY(), null);
	    }

    }

    private class KeyHandler implements KeyListener {
	private int respawnTime = 2000;

	@Override
	public void keyPressed(KeyEvent e) {
	    if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
		escape = true;
	    if (e.getKeyCode() == KeyEvent.VK_ENTER)
		enter = true;
	    if (e.getKeyCode() == KeyEvent.VK_LEFT)
		key_left = true;
	    if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		key_right = true;
	    if (e.getKeyCode() == KeyEvent.VK_SPACE)
		key_jump = true;
	    if (player.getRespawnLock() && (player.getTimeDead() > respawnTime))
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
		    player.setRespawnLock(false);
		    // player.setSayMessage(false);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	    if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
		escape = false;
	    if (e.getKeyCode() == KeyEvent.VK_ENTER)
		enter = false;
	    if (e.getKeyCode() == KeyEvent.VK_LEFT)
		key_left = false;
	    if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		key_right = false;
	    if (e.getKeyCode() == KeyEvent.VK_SPACE)
		key_jump = false;
	}

	// Unnötig
	@Override
	public void keyTyped(KeyEvent e) {
	}
    }

    public void sayEscape() {
	player.sayMessage("pres esc again to fak awf oder enter to stai");
	player.lockMessage();
	escaping = true;
    }

    public boolean getLastFrame() {
	return lastFrame;
    }

}
