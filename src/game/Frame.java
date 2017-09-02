package game;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import general.Config;
import general.KeyHandler;
import general.Message;
import map.Cube;
import map.Enemy;
import map.Lvl;
import map.Sweg;

class Frame extends JFrame {

    private int fps = 0;
    private int fpsCounter = 0;
    private long timeUntilLastSecond = System.currentTimeMillis() + 1000;

    private final Player player;
    private final Lvl lvl;

    private Message message = null;
    private final KeyHandler keyHandler = new KeyHandler();
    private final BufferStrategy bufferStrategy;

    Frame(Player player, Lvl lvl) {
        super("spodermens advenshur");

        addKeyListener(keyHandler);

        this.player = player;
        this.lvl = lvl;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Config.screenX, Config.screenY);

        setResizable(false);
        setVisible(true);

        createBufferStrategy(2);
        bufferStrategy = getBufferStrategy();

    }

    // screen zeichnen
    void repaintScreen() {

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
        for (Cube cube : lvl.getCubes()) {
            g.fillRect((int) cube.getBounding().getX(), (int) cube.getBounding().getY(), (int) cube.getBounding().getWidth(), (int) cube.getBounding().getHeight());
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

    private void sayMessage(Message newIncomingMessage, Graphics g) {

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
            message.updateTimer(1000 / Config.msPerFrame);
            if (message.getTimer() < 0)
                message = null;
        }
    }

    private void drawUnits(Graphics g) {
        g.drawImage(player.getLook(), player.getBounding().x, player.getBounding().y, null);
        if (lvl.getSwegArray() != null)
            for (Sweg sweg: lvl.getSwegArray()) {
                g.drawImage(sweg.getLook(), sweg.getBounding().x, sweg.getBounding().y, null);
            }
        if (lvl.getBigmekArray() != null) {
            g.drawImage(lvl.getBigmekArray().getLook(), (int) lvl.getBigmekArray().getBounding().getX(),
                    (int) lvl.getBigmekArray().getBounding().getY(), null);
        }
        if (lvl.getEnemyArray() != null)
            for (Enemy enemy: lvl.getEnemyArray()) {
                g.drawImage(enemy.getLook(), (int) enemy.getBounding().getX(), (int) enemy.getBounding().getY(), null);
            }

    }

    KeyHandler getKeyHandler() {
        return keyHandler;
    }

}
