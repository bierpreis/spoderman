package game;

import general.Config;
import map.Lvl;

public class Game {

    private final Lvl lvl;
    private final Player player;
    private final Frame frame;
    private NextAction nextAction;

    public Game(int lvlNumber) {
        lvl = new Lvl(lvlNumber);
        player = new Player(lvl);
        frame = new Frame(player, lvl);

    }

    public void run() {

        boolean running = true;
        while (running) {
            long startTime = System.nanoTime();
            running = player.update(frame.getKeyHandler());
            frame.repaintScreen();

            sleep(startTime);
        }
        setNextAction();
        frame.dispose();
    }

    private void setNextAction(){
        if(player.checkLvlUp())
            nextAction = NextAction.LVLUP;
        else nextAction = NextAction.EXIT;
    }

    private void sleep(long startTime) {
        int sleepTime = (int) (1000000000 / Config.targetFps - (System.nanoTime() - startTime)) / 1000000;
        if (sleepTime > 0)
            try {
                Thread.sleep(sleepTime);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    public NextAction getNextAction(){
        return nextAction;
    }

}
