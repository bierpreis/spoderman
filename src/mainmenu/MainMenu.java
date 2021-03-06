package mainmenu;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;

import game.Game;
import game.Config;
import game.KeyHandler;

public class MainMenu extends JFrame {

    private final Button[] buttonArray;
    private boolean showingMenu = true;
    private int msPerFrame = Integer.parseInt(Config.get("msPerFrame"));
    
    private int frameXSize = Integer.parseInt(Config.get("screenX"));
    private int frameYSize = Integer.parseInt(Config.get("screenY"));

    private int frameXPoint = Integer.parseInt(Config.get("windowX"));
    private int frameYPoint = Integer.parseInt(Config.get("windowY"));


    private final Screen screen;

    private volatile KeyHandler keyHandler = new KeyHandler();

    public MainMenu() {

        super("welcum to spodermens advenshur");

        screen = new Screen();

        screen.setBounds(0, 0, frameXSize, frameYSize);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(frameXPoint, frameYPoint, frameXSize, frameYSize);
        setVisible(true);
        setResizable(false);

        buttonArray = new Button[3];

        buttonArray[0] = new Button("new gaem");
        buttonArray[1] = new Button("lvl coed");
        buttonArray[2] = new Button("leaf");

        add(screen);

        addKeyListener(keyHandler);
        buttonArray[0].setFocus();
        requestFocus();

    }

    private class Screen extends JLabel {

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawButtons(g);
        }
    }

    private void drawButtons(Graphics g) {
        int buttonY = 100;

        drawButton(buttonArray[0], g, buttonY);
        drawButton(buttonArray[1], g, buttonY + 100);
        drawButton(buttonArray[2], g, buttonY + 200);
    }

    private void drawButton(Button button, Graphics g, int y) {

        if (button.getFocus()) {
            g.setColor(Color.RED);
        } else
            g.setColor(Color.BLUE);

        g.fillRect(button.x, y, button.width, button.height);

        int letterX[] = new int[button.getPicArray().length];

        letterX[0] = (int) button.getX();

        for (int j = 0; j < button.getPicArray().length; j++) {
            g.drawImage(button.getPicArray()[j], letterX[j], y, null);
            if (j < button.getPicArray().length - 1) {
                letterX[j + 1] = letterX[j] + 40;
            }
        }
    }

    private void updateButtonFocus() {
        for (int i = 0; i < buttonArray.length; i++) {
            buttonArray[i].update();

            // pfeil runter
            if (buttonArray[i].getFocus() && keyHandler.getDown() && !Button.isLocked()) {
                Button.setLock();
                if (i < buttonArray.length - 1) {
                    buttonArray[i + 1].setFocus();
                    buttonArray[i].unSetFocus();
                }
            }

            // pfeil hoch
            if (buttonArray[i].getFocus() && keyHandler.getUp() && !Button.isLocked()) {
                if (i > 0) {
                    buttonArray[i - 1].setFocus();
                    Button.setLock();
                    buttonArray[i].unSetFocus();
                }
            }
        }
    }

    private void doButtonActions() {
        if (buttonArray[0].getFocus() && keyHandler.getEnter()) {

            runGame(1);

        }
        if (buttonArray[1].getFocus() && keyHandler.getEnter()) {
            CodeInputDialog codeInput = new CodeInputDialog();
            if (codeInput.getUnlockedLvl() == 2) {
                runGame(2);
            }

        }
        if (buttonArray[2].getFocus() && keyHandler.getEnter()) {
            showingMenu = false;
        }
    }

    private void runGame(int lvl) {
        setVisible(false);
        Game.Action nextAction;
        Game game = new Game(lvl, keyHandler);
        nextAction = game.getNextAction();
        game.stop();
        if (nextAction.equals(Game.Action.LVLUP))
            runGame(lvl + 1);
        setVisible(true);
    }

    public void showMenu() {
        while (showingMenu) {

            updateButtonFocus();
            screen.repaint();

            doButtonActions();

            try {
                Thread.sleep(msPerFrame);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


}
