package game;

import general.Config;
import general.KeyHandler;
import map.Lvl;
import map.Player;

public class Game {

    private final Player player;
    private final Frame frame;
    private Lvl lvl;
    private KeyHandler keyHandler;
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

    private void start() {

        while (action == Action.RUNNING) {
            long startTime = System.nanoTime();


            double delta = 1;

            lvl.update(player.getScrollingLeft(), player.getScrollingRight(), delta);

            player.update(delta);
            frame.draw();
            action = checkNextAction();

            long updateLength = System.nanoTime() - startTime;


            int sleepTime = (int) (Config.msPerFrame - (updateLength / 1_000_000));
            sleep(sleepTime);
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

        if (lvl.getBigmekArray() != null)
            if (lvl.getBigmekArray().getCollected())
                if (keyHandler.getEnter()) {
                    System.out.println("action lvl up returned!");
                    return Action.LVLUP;
                }

        if (keyHandler.getEscape())
            showEscDialog = true;
        if (showEscDialog) {
            player.createMessage("press enter to exit");
            if(keyHandler.getEnter())
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
