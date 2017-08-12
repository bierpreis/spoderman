package game;

import general.Config;
import map.Lvl;

public class Game {

    private final Lvl lvl;
    private final int nsPerFrame = 1000000 * Config.getMsPerFrame();
    private final Player player;
    private final Frame f;

    public Game(int lvlNumber) {
        lvl = new Lvl(lvlNumber);
        player = new Player(lvl);
        f = new Frame(player, lvl);

        if (update() == "LVL_UP") {
            new Game(lvlNumber + 1);
        }

    }

    private String update() {

        String nextAction = "CONTINUE";
        while (nextAction == "CONTINUE") {
            long startTime = System.nanoTime();
            nextAction = player.update(f.getKeyHandler());
            f.repaintScreen();

            // check iv lvl up

            if (!f.isDisplayable()) {
                f.dispose();
                break;
            }

            int sleepTime = (int) (nsPerFrame - (System.nanoTime() - startTime)) / 1000000;
            if (sleepTime > 0)
                try {
                    Thread.sleep(sleepTime);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        }
        f.dispose();
        // sleep to avoid to start same lvl again with enter press
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return nextAction;
    }

}
