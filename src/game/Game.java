package game;

public class Game {
    
    boolean lvlUp = false;
    Lvl lvl;
    int nsPerFrame = 1000000000 / Config.getTargetFps();
    Player player;
    Frame f;

    public Game(int lvlNumber) {
	lvl = new Lvl(lvlNumber);
	player = new Player(lvl);
	f = new Frame(player, lvl);

	lvlUp = update();
	System.out.println("lvlup" + lvlUp);
	if(lvlUp)
	    new Game(lvlNumber+1);
	
	

    }

    public boolean update() {

	String nextAction = "CONTINUE";
	while (nextAction == "CONTINUE") {
	    long startTime = System.nanoTime();
	    nextAction = player.update(f.getKeyHandler());
	    f.repaintScreen();

	    // check iv lvl up
	    if (player.getBigmekCollected())
		return true;

	    
	    if (!f.isDisplayable())
		break;

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

	return false;
    }

}
