package game;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import general.Config;
import general.KeyHandler;
import general.Message;
import map.Lvl;

public class Frame extends JFrame {

    private int fps = 0;
    private int fpsCounter = 0;
    private long timeUntilLastSecond = System.currentTimeMillis() + 1000;

    private final Player player;
    private Lvl lvl;

    private Message message = null;
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

	drawCubes(g);
	sayMessage(player.getNewMessage(), g);
	drawUnits(g);
	writeInfo(g, player);


	if (isDisplayable())
	    bufferStrategy.show();
	g.dispose();

    }

    private void writeInfo(Graphics g, Player player) {
	g.setColor(Color.BLACK);
	g.drawString("Sweg Count:" + player.getSwegCount(), 25, 50);
	g.drawString("Fagits rekt:" + player.getKills(), 150, 50);
	g.drawString("Sp0der Lyfez :" + player.getLifes(), 300, 50);
	g.drawString("FPS: " + calcFps(), 600, 50);
    }

    private void drawCubes(Graphics g) {
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
    }

    private int calcFps() {
	fpsCounter++;
	if (System.currentTimeMillis() > timeUntilLastSecond) {
	    timeUntilLastSecond = System.currentTimeMillis() + 1000;
	    fps = fpsCounter;
	    fpsCounter = 0;
	}
	return fps;

    }

    private Message sayMessage(Message newIncomingMessage, Graphics g) {

	if (newIncomingMessage != null)
	    message = newIncomingMessage;
	if (message != null) {
	    int letterX[] = new int[message.getMessagePicArray().length];
	    int letterY[] = new int[message.getMessagePicArray().length];
	    letterX[0] = 0;
	    letterY[0] = 40;
	    for (int i = 0; i < message.getMessagePicArray().length; i++) {
		g.drawImage(message.getMessagePicArray()[i], letterX[i], letterY[i], null);
		if (i < message.getMessagePicArray().length - 1) {
		    if (letterX[i] > 600) {
			letterX[i + 1] = 40;
			letterY[i + 1] = letterY[i] + 80;
		    } else {
			letterX[i + 1] = letterX[i] + 40;
			letterY[i + 1] = letterY[i];
		    }
		}
	    }
	    message.updateTimer(1000 / Config.getTargetFps());
	    if (message.getTimer() < 0)
		message = null;
	}

	return newIncomingMessage;

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

    public KeyHandler getKeyHandler() {
	return keyHandler;
    }

}
