package game;

import general.Config;
import map.Lvl;

public class Game {

    private final Lvl lvl;
    private final int nsPerFrame = 1000000000 / Config.targetFps;
    private final Player player;
    private final Frame f;

    public Game(int lvlNumber) {
        lvl = new Lvl(lvlNumber);
        player = new Player(lvl);
        f = new Frame(player, lvl);

        if (update() == "LVLUP") {
            new Game(lvlNumber + 1);
        }

    }

    private String update() {

        boolean running = true;
        while (running) {
            long startTime = System.nanoTime();
            running = player.update(f.getKeyHandler());
            f.repaintScreen();

            // check iv lvl up
            if (!f.isDisplayable()) {
                f.dispose();
                break;
            }
            sleep(startTime);
        }

        f.dispose();

        // sleep to avoid to start same lvl again with enter press
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return player.getNextAction();
    }

    private void sleep(long startTime) {
        int sleepTime = (int) (nsPerFrame - (System.nanoTime() - startTime)) / 1000000;
        if (sleepTime > 0)
            try {
                Thread.sleep(sleepTime);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

}
