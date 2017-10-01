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

        while (player.getRunning()) {
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            System.out.println("update length" + updateLength);
            lastLoopTime = now;
            double delta = updateLength / ((double)Config.msPerFrame*1_000_000);

            player.update(delta);
            frame.draw();

            int sleepTime = (int) (lastLoopTime - System.nanoTime()) / 1_000_000 + Config.msPerFrame;

            sleep(sleepTime);


        }
        nextAction = player.getNextAction();
    }

    private void sleep(int sleepTime){
        if (sleepTime > 0)
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException ie) {
                System.out.println("interrupted !");
            }

    }

    public void stop(){
        frame.dispose();
    }


    public NextAction getNextAction() {
        return nextAction;
    }

}
