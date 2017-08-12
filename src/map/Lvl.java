package map;

public class Lvl {

    private Cube[] cube;
    private Sweg[] sweg;
    private Bigmek bigmek;
    private Enemy[] enemy;

    public Lvl(int lvlNumber) {
	createCube(lvlNumber);
	createSweg(lvlNumber);
	createEnemy(lvlNumber);
	createBigmek(lvlNumber);
    }

    private void createCube(int lvlNumber) {
	if (lvlNumber == 1) {

	    cube = new Cube[17]; // x, y, width, height ist das format
	    cube[0] = new Cube(0, 550, 3700, 20); // boden
	    cube[1] = new Cube(150, 510, 50, 50);
	    cube[2] = new Cube(250, 510, 50, 50);
	    cube[3] = new Cube(300, 460, 50, 50);
	    cube[4] = new Cube(350, 410, 50, 50);
	    cube[5] = new Cube(400, 450, 50, 50);
	    cube[6] = new Cube(450, 450, 50, 50);
	    cube[7] = new Cube(-50, -100, 50, 1500); // linke aussengrenze
	    cube[8] = new Cube(3700, -100, 50, 1500); // rechte ausssengrenze
	    cube[9] = new Cube(800, 650, 50, 50);
	    cube[10] = new Cube(1200, 400, 600, 50);
	    cube[11] = new Cube(1000, 510, 50, 50);
	    cube[12] = new Cube(1850, 250, 50, 50);
	    cube[13] = new Cube(2100, 400, 500, 50);
	    cube[14] = new Cube(2700, 300, 400, 50);
	    cube[15] = new Cube(3200, 200, 300, 50);
	    cube[16] = new Cube(3650, 200, 50, 50);
	}

	if (lvlNumber == 2) {
	    cube = new Cube[5];
	    cube[0] = new Cube(0, 550, 3700, 20);
	    cube[1] = new Cube(150, 510, 50, 50);
	    cube[2] = new Cube(250, 510, 50, 50);
	    cube[3] = new Cube(950, 400, 50, 300);
	    cube[4] = new Cube(1250, 400, 50, 300);

	}

    }

    private void createSweg(int lvlNumber) {
	if (lvlNumber == 1) {
	    sweg = new Sweg[6];
	    sweg[0] = new Sweg(500, 500);
	    sweg[1] = new Sweg(550, 500);
	    sweg[2] = new Sweg(600, 500);
	    sweg[3] = new Sweg(3300, 150);
	    sweg[4] = new Sweg(3330, 150);
	    sweg[5] = new Sweg(1850, 200);

	}
	if (lvlNumber == 2) {
	    sweg = new Sweg[3];
	    sweg[0] = new Sweg(1000, 500);
	    sweg[1] = new Sweg(1100, 500);
	    sweg[2] = new Sweg(1200, 500);
	}

    }

    private void createBigmek(int lvlNumber) {
	if (lvlNumber == 1) {
	    bigmek = new Bigmek(3650, 150);
	}

    }

    private void createEnemy(int lvlNumber) {
	if (lvlNumber == 1) {
	    enemy = new Enemy[2];
	    enemy[0] = new Enemy(500, 500, "dolan", cube);
	    enemy[1] = new Enemy(1500, 500, "gooby", cube);
	}
	if (lvlNumber == 2) {
	    enemy = new Enemy[2];
	    enemy[0] = new Enemy(1000, 500, "dolan", cube);
	    enemy[1] = new Enemy(1100, 500, "gooby", cube);
	}
    }

    // getter
    public Cube[] getCubes() {
	return cube;
    }

    public Sweg[] getSweg() {
	return sweg;
    }

    public Bigmek getBigmek() {
	return bigmek;
    }

    public Enemy[] getEnemy() {
	return enemy;
    }

    public void update(boolean scrollingLeft, boolean scrollingRight) {
	for (int i = 0; i < cube.length; i++) {
	    cube[i].updateBounding(scrollingLeft, scrollingRight);
	}

	if (bigmek != null) {
	    bigmek.getBounding().scroll(scrollingLeft, scrollingRight);
	}

	if (enemy != null)
	    for (int i = 0; i < enemy.length; i++) {
		enemy[i].update(scrollingLeft, scrollingRight);
	    }

	if (enemy != null)
	    for (int i = 0; i < sweg.length; i++) {
		sweg[i].getBounding().scroll(scrollingLeft, scrollingRight);
	    }
    }

}
