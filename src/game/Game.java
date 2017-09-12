package game;

import general.Config;
import general.KeyHandler;
import map.Lvl;

public class Game {

    private final Player player;
    private final Frame frame;
    private NextAction nextAction;
    private boolean running = true;

    public Game(int lvlNumber, KeyHandler keyHandler) {
        Lvl lvl = new Lvl(lvlNumber);
        player = new Player(lvl, keyHandler);
        frame = new Frame(player, lvl, keyHandler, running);
        start();
    }

    public void start() {
        long lastLoopTime = System.nanoTime();

        while (running) {
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            double delta = updateLength / Config.msPerFrame;

            player.update();
            frame.draw();
            running = player.getRunning();

            int sleepTime = (int) (lastLoopTime - System.nanoTime()) / 1_000_000 + Config.msPerFrame;
            System.out.println("sleeptime: " + sleepTime);
            if (sleepTime > 0)
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException ie) {
                    System.out.println("interrupted !");
                }


        }
        nextAction = player.getNextAction();
    }

    public void stop(){
        frame.dispose();
    }


    public NextAction getNextAction() {
        return nextAction;
    }

}
