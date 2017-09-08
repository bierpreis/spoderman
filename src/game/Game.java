package game;

import general.KeyHandler;
import map.Lvl;

import java.util.concurrent.atomic.AtomicBoolean;

public class Game {

    private final Player player;
    private final Frame frame;
    private NextAction nextAction;
    private AtomicBoolean running = new AtomicBoolean(true);

    public Game(int lvlNumber, KeyHandler keyHandler) {
        Lvl lvl = new Lvl(lvlNumber);
        player = new Player(lvl, keyHandler, running);
        frame = new Frame(player, lvl, keyHandler, running);
    }

    public void start() {
        Thread graphics = new Thread(frame);
        Thread logics = new Thread(player);

        graphics.start();
        logics.start();

        while(running.get()) {
            try {
                Thread.sleep(200);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        nextAction = player.getNextAction();

    }



    public NextAction getNextAction(){
        return nextAction;
    }

}
