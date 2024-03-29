package game;

import graphics.Frame;
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
    private int msPerFrame = Integer.parseInt(Config.get("msPerFrame"));
    private Camera camera;
    private int escTime = Integer.parseInt(Config.get("escTime"));


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

        //loadPropertes();

        MainMenu menu = new MainMenu();
        menu.showMenu();

        menu.dispose();

        System.exit(0);

    }


    private void start() {
        Audio.play(Sound.MONEY);

        while (action == Action.RUNNING) {
            long startTime = System.currentTimeMillis();


            player.update();
            lvl.updateGameObjects();
            frame.draw(player, lvl, camera);
            action = checkNextAction();
            camera.tick(player);

            long updateLength = System.currentTimeMillis() - startTime;

            sleep((int) (msPerFrame - updateLength));
        }
    }


    private void sleep(int sleepTime) {
        if (sleepTime > 0)
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
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

            escapingTime += msPerFrame;
            if (keyHandler.getEnter()) action = Action.EXIT;
            if (escapingTime > escTime) {
                escapingTime = 0;
                showEscDialog = false;
            }
        }
        return Action.RUNNING;
    }

    public Action getNextAction() {
        return action;
    }

    public enum Action {
        RUNNING, LVLUP, EXIT
    }

}
