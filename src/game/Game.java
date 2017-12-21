package game;

import helpers.Action;
import helpers.Config;
import helpers.KeyHandler;
import map.Lvl;
import map.Player;
import menu.Menu;

public class Game {

    private final Player player;
    private final Frame frame;
    private final Lvl lvl;
    private final KeyHandler keyHandler;
    private Action action = Action.RUNNING;
    private boolean showEscDialog = false;
    private int escapingTime = 0;

    public Game(int lvlNumber, KeyHandler keyHandler) {
        lvl = new Lvl(lvlNumber);
        player = new Player(lvl, keyHandler);
        frame = new Frame(player, lvl, keyHandler);
        this.keyHandler = keyHandler;
        start();
    }

    public static void main(String[] args) {

        Menu menu = new Menu();
        menu.showMenu();

        menu.dispose();

        System.exit(0);

    }

    private void start() {

        while (action == Action.RUNNING) {
            long startTime = System.nanoTime();

            lvl.update(player.getScrollingLeft(), player.getScrollingRight());

            player.update();
            frame.draw();
            action = checkNextAction();

            long updateLength = System.nanoTime() - startTime;

            sleep((int) (Config.msPerFrame - (updateLength / 1_000_000)));
        }
    }


    private void sleep(int sleepTime) {
        if (sleepTime > 0)
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException ie) {
                System.out.println("interrupted !");
            }

    }


    public void stop() {
        frame.dispose();
    }

    private Action checkNextAction() {

        if (lvl.getBigmekArray()!= null)
            if (lvl.getBigmekArray()[0].getCollected())
                if (keyHandler.getEnter())
                    return Action.LVLUP;


        if (keyHandler.getEscape())
            showEscDialog = true;
        if (showEscDialog) {
            player.createMessage("press enter to exit");
            if (keyHandler.getEnter())
                return Action.EXIT;

            escapingTime += Config.msPerFrame;
            if (keyHandler.getEnter()) action = Action.EXIT;
            if (escapingTime > Config.escTime) {
                escapingTime = 0;
                showEscDialog = false;
            }
        }
        return Action.RUNNING;
    }

    public Action getNextAction() {
        return action;
    }
}
