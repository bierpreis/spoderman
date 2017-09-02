package game;

import general.Config;
import map.Lvl;

public class Game {

    private final Lvl lvl;
    private final Player player;
    private final Frame frame;

    public Game(int lvlNumber) {
        lvl = new Lvl(lvlNumber);
        player = new Player(lvl);
        frame = new Frame(player, lvl);
        run();
    }

    private void run() {

        boolean running = true;
        while (running) {
            long startTime = System.nanoTime();
            running = player.update(frame.getKeyHandler());
            frame.repaintScreen();

            // check iv lvl up
            if (!frame.isDisplayable()) {
                frame.dispose();
                break;
            }
            sleep(startTime);
        }

        frame.dispose();

        if(player.getLvlUp()) {
            new Game(lvl.getLvlNumber()+1);
        }


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

}
