package game;

public class Game {

    Lvl lvl;
    int nsPerFrame = 1000000000 / Config.getTargetFps();
    Player player;
    Frame f;
    boolean lvlUp = false;

    public Game(int lvlNumber) {
	lvl = new Lvl(lvlNumber);
	player = new Player(lvl);
	f = new Frame(player, lvl);

	update();
	System.out.println("nach update");
	if (lvlUp)
	    new Game(lvlNumber +1);
	System.out.println("nach lvlUp");
	
    }

    public void update() {



	while (true) {
	    long startTime = System.nanoTime();
	    player.update(f.getKeyHandler());
	    f.repaintScreen();
	    if(player.getBigMekCollected())
		break;
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

	
    }
    


}
