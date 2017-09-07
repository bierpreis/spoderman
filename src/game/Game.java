package game;

import general.Config;
import general.KeyHandler;
import map.Lvl;

public class Game {

    private final Lvl lvl;
    private final Player player;
    private final Frame frame;
    private NextAction nextAction;

    public Game(int lvlNumber, KeyHandler keyHandler) {
        lvl = new Lvl(lvlNumber);
        player = new Player(lvl, keyHandler);
        frame = new Frame(player, lvl, keyHandler);
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


    public NextAction getNextAction(){
        return nextAction;
    }

}
