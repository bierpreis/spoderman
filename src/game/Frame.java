package game;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Frame extends JFrame {

    private boolean lastFrame = false;
    boolean firstTime = true;
    private boolean escaping;

    int escapeTime = 0; // esc dialog
    private int fps = 0;
    private int fpsCounter = 0;
    private long timeUntilLastSecond = System.currentTimeMillis() + 1000;

    private final Player player;
    private Lvl lvl;

    private KeyHandler keyHandler = new KeyHandler();
    private BufferStrategy bufferStrategy;

    public Frame(Player player, Lvl lvl) {
	super("spodermens advenshur");

	addKeyListener(keyHandler);

	this.player = player;
	this.lvl = lvl;

	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setSize(Config.getScreenX(), Config.getScreenY());

	setResizable(false);
	setVisible(true);

	createBufferStrategy(2);
	bufferStrategy = getBufferStrategy();

    }

    // screen zeichnen
    public void repaintScreen() {

	Graphics g = bufferStrategy.getDrawGraphics();

	g.setColor(Color.lightGray);
	g.fillRect(0, 0, getWidth(), getHeight());

	g.setColor(Color.BLUE);
	for (int i = 0; i < lvl.getCubes().length; i++) {
	    // if(i == 0 && firstTime){
	    // System.out.println("bounding: " +
	    // lvl.getCubes()[1].getBounding());
	    // System.out.println("topBounding: " +
	    // lvl.getCubes()[1].getTopBounding());
	    // System.out.println("botBounding: " +
	    // lvl.getCubes()[1].getBotBounding());
	    // System.out.println("leftBbounding: " +
	    // lvl.getCubes()[1].getLeftBounding());
	    // System.out.println("rightBounding: " +
	    // lvl.getCubes()[1].getRightBounding());
	    // firstTime = false;
	    // }
	    g.fillRect((int) lvl.getCubes()[i].getBounding().getX(), (int) lvl.getCubes()[i].getBounding().getY(),
		    (int) lvl.getCubes()[i].getBounding().getWidth(),
		    (int) lvl.getCubes()[i].getBounding().getHeight());
	}

	sayMessage(player.isSayMessage(), g);
	drawUnits(g);
	writeInfo(g, player, calcFps());
	



	// macht den escape dialog
	// if (getKeyListeners()[0].getEscape()) {
	// sayEscape();
	// }
	// if (escaping)
	// escapeTime += 15;
	// if (escaping && keyEscape && escapeTime > 600) {
	// lastFrame = true;
	// dispose();
	// }
	// if (escaping && keyEnter) {
	// escaping = false;
	// player.unlockMessage();
	// escapeTime = 0;
	// }

	if (this.isDisplayable())
	    bufferStrategy.show();
	g.dispose();

    }
    
    private void writeInfo(Graphics g, Player player, int fps){
	g.setColor(Color.BLACK);
	g.drawString("Sweg Count:" + player.getSwegCount(), 25, 50);
	g.drawString("Fagits rekt:" + player.getKills(), 150, 50);
	g.drawString("Sp0der Lyfez :" + player.getLifes(), 300, 50);
	g.drawString("FPS: " + calcFps(), 600, 50);
    }

    // zeichnen

    private int calcFps() {
	fpsCounter++;
	if (System.currentTimeMillis() > timeUntilLastSecond) {
	    timeUntilLastSecond = System.currentTimeMillis() + 1000;
	    fps = fpsCounter;
	    fpsCounter = 0;
	}
	return fps;

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
	    g.drawImage(lvl.getBigmek().getLook(), (int) lvl.getBigmek().getBounding().getX(),
		    (int) lvl.getBigmek().getBounding().getY(), null);
	}
	if (lvl.getEnemy() != null)
	    for (int i = 0; i < lvl.getEnemy().length; i++) {
		g.drawImage(lvl.getEnemy()[i].getLook(), (int) lvl.getEnemy()[i].getBounding().getX(),
			(int) lvl.getEnemy()[i].getBounding().getY(), null);
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
    
    public KeyHandler getKeyHandler(){
	return keyHandler;
    }

}
