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

    public void start() {
        Thread graphics = new Thread(frame);
        Thread logics = new Thread(player);

        graphics.start();
        logics.start();

        while(!logics.isInterrupted() && !graphics.isInterrupted())
            try {
                Thread.sleep(200);
            } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("after sleep");
        graphics.interrupt();
        logics.interrupt();

        setNextAction();
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
