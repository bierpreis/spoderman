package game;

public class Map {

    private int schwerkraft = 5;

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

    public Cube[] getCube() {
	return cube;
    }
}
