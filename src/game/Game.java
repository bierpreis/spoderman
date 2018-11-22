package game;

import graphics.Frame;
import map.Action;
import map.Lvl;
import graphics.Camera;
import player.Player;
import mainmenu.MainMenu;

public class Game {

    private final Player player;
    private final Frame frame;
    private final Lvl lvl;
    private final KeyHandler keyHandler;
    private Action action = Action.RUNNING;
    private boolean showEscDialog = false;
    private int escapingTime = 0;
    private Camera camera;

    public Game(int lvlNumber, KeyHandler keyHandler) {
        lvl = new Lvl();
        lvl.createFromFile("lvl" + Integer.toString(lvlNumber));
        player = new Player(lvl, keyHandler);
        frame = new Frame(keyHandler);
        this.keyHandler = keyHandler;
        camera = new Camera(0, 0);
        start();
    }

    public static void main(String[] args) {

        MainMenu menu = new MainMenu();
        menu.showMenu();

        menu.dispose();

        System.exit(0);

    }

    private void start() {
        Sound.MONEY.play();

        while (action == Action.RUNNING) {
            long startTime = System.currentTimeMillis();


            player.update();
            lvl.updateGameObjects();
            frame.draw(player, lvl, camera);
            action = checkNextAction();
            camera.tick(player);

            long updateLength = System.currentTimeMillis() - startTime;

            sleep((int) (Config.msPerFrame - updateLength));
        }
    }


    private void sleep(int sleepTime) {
        if (sleepTime > 0)
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException ie) {
                System.out.println("interrupted !");
            }

    }


    public void stop() {
        frame.dispose();
    }

    private Action checkNextAction() {


        if (lvl.isBigmekCollected())
            if (keyHandler.getEnter())
                return Action.LVLUP;


        if (keyHandler.getEscape())
            showEscDialog = true;
        if (showEscDialog) {
            player.createMessage("press enter to exit");
            if (keyHandler.getEnter())
                return Action.EXIT;

            escapingTime += Config.msPerFrame;
            if (keyHandler.getEnter()) action = Action.EXIT;
            if (escapingTime > Config.escTime) {
                escapingTime = 0;
                showEscDialog = false;
            }
        }
        return Action.RUNNING;
    }

    public Action getNextAction() {
        return action;
    }
}
