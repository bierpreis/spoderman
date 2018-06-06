package game;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import helpers.Config;
import helpers.KeyHandler;
import helpers.Message;
import map.*;
import map.enemies.AbstractEnemy;
import player.Player;

class Frame extends JFrame {

    private final FpsCounter fpsCounter;

    private Message message = null;
    private final BufferStrategy bufferStrategy;

    Frame(KeyHandler keyHandler) {
        super("spodermens advenshur");

        addKeyListener(keyHandler);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(Config.windowX, Config.windowY, Config.screenX, Config.screenY);

        setResizable(false);
        setVisible(true);

        createBufferStrategy(3);
        bufferStrategy = getBufferStrategy();
        requestFocus();
        fpsCounter = new FpsCounter();

    }


    void draw(Player player, Lvl lvl) {

        Graphics g = bufferStrategy.getDrawGraphics();

        g.setColor(Color.lightGray);
        g.fillRect(0, 0, getWidth(), getHeight());

        drawCubes(g, lvl);
        displayMessage(player.pollNewMessage(), g);
        drawUnits(g, player, lvl);
        writeInfo(g, player);


        bufferStrategy.show();


        g.dispose();


    }


    private void writeInfo(Graphics g, Player player) {
        g.setColor(Color.BLACK);
        g.drawString("Sweg Count:" + player.getSwegCount(), 25, 50);
        g.drawString("Fagits rekt:" + player.getKills(), 150, 50);
        g.drawString("Sp0der Lyfez :" + player.getLifes(), 300, 50);
        g.drawString("FPS: " + fpsCounter.calcFps(), 600, 50);
    }

    private void drawCubes(Graphics g, Lvl lvl) {
        g.setColor(Color.BLUE);
        for (Cube cube : lvl.getCubes()) {
            g.fillRect((int) cube.getX(), (int) cube.getY(), (int) cube.getBounding().getWidth(), (int) cube.getBounding().getHeight());
        }
    }


    private void displayMessage(Message newIncomingMessage, Graphics g) {

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
            message.updateTimer();
            if (message.getTimer() < 0)
                message = null;
        }
    }

    private void drawUnits(Graphics g, Player player, Lvl lvl) {
        newDrawUnits(g, player);
        newDrawUnits(g, player.getHat());

        for (Sweg sweg : lvl.getSwegList()) {
            newDrawUnits(g, sweg);
        }

        for (Bigmek bigmek : lvl.getBigmekList())
            newDrawUnits(g, bigmek);


        for (AbstractEnemy enemy : lvl.getEnemyList()) {
            newDrawUnits(g, enemy);
        }

    }

    private void newDrawUnits(Graphics g, AbstractMapObject mapObject) {

        g.drawImage(mapObject.getLook(), mapObject.getX(), mapObject.getY(), null);

    }

}
