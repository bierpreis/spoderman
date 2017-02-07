package game;

public class Map {

    private int schwerkraft = 5;
    private int moveSpeed = 5;

    private Lvl lvl;

    private Cube[] cube;
    private Sweg[] sweg;
    private Enemy[] enemy;
    private Bigmek bigmek;

    // konstruktor: getCube(lvl) etc
    public Map(int lvlnr) {
	lvl = new Lvl(lvlnr);
	cube = lvl.getCubes();
	enemy = lvl.getEnemy();
	bigmek = lvl.getBigmek();
	sweg = lvl.getSweg();
    }

    public int getSchwerkraft() {
	return schwerkraft;
    }

    public int getMoveSpeed() {
	return moveSpeed;
    }

    public Sweg[] getSweg() {
	return sweg;
    }

    public Enemy[] getEnemies() {
	return enemy;
    }

    Bigmek getBigmek() {
	return bigmek;
    }

    // cube updaten
    public Cube[] scrollCube(boolean scrollingRight, boolean scrollingLeft) {
	if (scrollingRight) {
	    for (int i = 0; i < getCube().length; i++) {
		getCube()[i].x -= moveSpeed;
	    }
	}
	if (scrollingLeft) {
	    for (int i = 0; i < getCube().length; i++) {
		getCube()[i].x += moveSpeed;
	    }
	}
	return cube;
    }

    public Cube[] getCube() {
	return cube;
    }
}
