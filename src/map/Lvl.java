package map;

import map.enemies.Dolan;
import map.enemies.Enemy;
import map.enemies.Gooby;

public class Lvl {

    private Cube[] cubeArray;
    private Sweg[] swegArray;
    private Bigmek bigmekArray;
    private Enemy[] enemyArray;

    public Lvl(int lvlNumber) {
        createCube(lvlNumber);
        createSweg(lvlNumber);
        createEnemy(lvlNumber);
        createBigmek(lvlNumber);
    }

    private void createCube(int lvlNumber) {
        if (lvlNumber == 1) {

            cubeArray = new Cube[17]; // x, y, width, height ist das format
            cubeArray[0] = new Cube(0, 550, 3700, 20); // boden
            cubeArray[1] = new Cube(150, 510, 50, 50);
            cubeArray[2] = new Cube(250, 510, 50, 50);
            cubeArray[3] = new Cube(300, 460, 50, 50);
            cubeArray[4] = new Cube(350, 410, 50, 50);
            cubeArray[5] = new Cube(400, 450, 50, 50);
            cubeArray[6] = new Cube(450, 450, 50, 50);
            cubeArray[7] = new Cube(-50, -100, 50, 1500); // linke aussengrenze
            cubeArray[8] = new Cube(3700, -100, 50, 1500); // rechte ausssengrenze
            cubeArray[9] = new Cube(800, 650, 50, 50);
            cubeArray[10] = new Cube(1200, 400, 600, 50);
            cubeArray[11] = new Cube(1000, 510, 50, 50);
            cubeArray[12] = new Cube(1850, 250, 50, 50);
            cubeArray[13] = new Cube(2100, 400, 500, 50);
            cubeArray[14] = new Cube(2700, 300, 400, 50);
            cubeArray[15] = new Cube(3200, 200, 300, 50);
            cubeArray[16] = new Cube(3650, 200, 50, 50);
        }

        if (lvlNumber == 2) {
            cubeArray = new Cube[5];
            cubeArray[0] = new Cube(0, 550, 3700, 20);
            cubeArray[1] = new Cube(150, 510, 50, 50);
            cubeArray[2] = new Cube(250, 510, 50, 50);
            cubeArray[3] = new Cube(950, 400, 50, 300);
            cubeArray[4] = new Cube(1250, 400, 50, 300);

        }

    }

    private void createSweg(int lvlNumber) {
        if (lvlNumber == 1) {
            swegArray = new Sweg[6];
            swegArray[0] = new Sweg(500, 500);
            swegArray[1] = new Sweg(550, 500);
            swegArray[2] = new Sweg(600, 500);
            swegArray[3] = new Sweg(3300, 150);
            swegArray[4] = new Sweg(3330, 150);
            swegArray[5] = new Sweg(1850, 200);

        }
        if (lvlNumber == 2) {
            swegArray = new Sweg[3];
            swegArray[0] = new Sweg(1000, 500);
            swegArray[1] = new Sweg(1100, 500);
            swegArray[2] = new Sweg(1200, 500);
        }

    }

    private void createBigmek(int lvlNumber) {
        if (lvlNumber == 1) {
            bigmekArray = new Bigmek(3650, 150);
        }

    }

    private void createEnemy(int lvlNumber) {
        if (lvlNumber == 1) {
            enemyArray = new Enemy[2];
            enemyArray[0] = new Dolan(500, 500, cubeArray);
            enemyArray[1] = new Gooby(1500, 500, cubeArray);
        }
        if (lvlNumber == 2) {
            enemyArray = new Enemy[2];
            enemyArray[0] = new Dolan(1000, 500, cubeArray);
            enemyArray[1] = new Gooby(1100, 500, cubeArray);
        }
    }

    // getter
    public Cube[] getCubes() {
        return cubeArray;
    }

    public Sweg[] getSwegArray() {
        return swegArray;
    }

    public Bigmek getBigmekArray() {
        return bigmekArray;
    }

    public Enemy[] getEnemyArray() {
        return enemyArray;
    }

    public void update(boolean scrollingLeft, boolean scrollingRight, double delta) {
        for (Cube cube : cubeArray)
            cube.updateBounding(scrollingLeft, scrollingRight, delta);

        if (bigmekArray != null)
            bigmekArray.scroll(scrollingLeft, scrollingRight, delta);

        if (enemyArray != null)
            for (Enemy enemy : enemyArray)
                enemy.update(scrollingLeft, scrollingRight, delta);

        if (swegArray != null)
            for (Sweg sweg : swegArray)
                sweg.scroll(scrollingLeft, scrollingRight, delta);

    }

}
