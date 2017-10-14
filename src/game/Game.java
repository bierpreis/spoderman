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

        while (player.getRunning()) {
            long startTime = System.nanoTime();



            double delta = 1;

            player.update(delta);
            frame.draw();

            long updateLength = startTime - System.nanoTime();
            

            int sleepTime = (int) (Config.msPerFrame-(updateLength/1_000_000));
            System.out.println("sleeptime " + sleepTime);
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
