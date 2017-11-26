package map;

import map.enemies.Dolan;
import map.enemies.AbstractEnemy;
import map.enemies.Gooby;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Lvl {

    private Cube[] cubeArray;
    private Sweg[] swegArray;
    private Bigmek[] bigmekArray;
    private AbstractEnemy[] enemyArray;

    public Lvl(int lvlNumber) {


        String pathToFile = "lvl" + Integer.toString(lvlNumber) + ".txt";
        FileReader fr;
        try {
            fr = new FileReader(pathToFile);
        } catch (FileNotFoundException e) {
            System.out.println("could not find lvl file");
            return;
        }

        BufferedReader br = new BufferedReader(fr);
        LinkedList<String> cubeStringList = new LinkedList();
        LinkedList<String> enemyStringList = new LinkedList();
        LinkedList<String> swegStringList = new LinkedList();
        LinkedList<String> bigmekStringList = new LinkedList();
        int lineNumber = 0;
        try {

            while (true) {
                String newLine = br.readLine();
                if (!newLine.equals("END_CUBES")) {
                    cubeStringList.add(newLine);
                } else break;
                lineNumber++;
            }

            while (true) {
                String newLine = br.readLine();
                if (!newLine.equals("END_ENEMIES")) {
                    enemyStringList.add(newLine);
                } else break;
                lineNumber++;
            }

            while (true) {
                String newLine = br.readLine();
                if (!newLine.equals("END_SWEG")) {
                    swegStringList.add(newLine);
                } else break;
                lineNumber++;
            }

            while (true) {
                String newLine = br.readLine();
                if (!newLine.equals("END_BIGMEK")) {
                    bigmekStringList.add(newLine);
                } else break;
                lineNumber++;
            }


        } catch (Exception e) {
            System.out.println("error in map file line " + lineNumber);
        }

        cubeArray = createCubes(cubeStringList);
        enemyArray = createEnemies(enemyStringList);
        swegArray = createSweg(swegStringList);
        bigmekArray = createBigmek(bigmekStringList);

    }


    static Cube[] createCubes(List<String> cubeStringList) {

        Cube[] cubeArray = new Cube[cubeStringList.size()];

        for (int i = 0; i < cubeStringList.size(); i++) {
            Scanner scanner = new Scanner(cubeStringList.get(i));
            cubeArray[i] = new Cube(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
        }

        return cubeArray;
    }

    static AbstractEnemy[] createEnemies(List<String> enemyString) {
        AbstractEnemy[] enemyArray = new AbstractEnemy[enemyString.size()];
        for (int i = 0; i < enemyString.size(); i++) {
            Scanner scanner = new Scanner(enemyString.get(i));
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            String type = scanner.next();
            if (type.equals("dolan"))
                enemyArray[i] = new Dolan(x, y);
            if (type.equals("gooby"))
                enemyArray[i] = new Gooby(x, y);

        }
        return enemyArray;
    }

    static Sweg[] createSweg(List<String> swegString) {
        Sweg[] swegs = new Sweg[swegString.size()];
        for (int i = 0; i < swegs.length; i++) {
            Scanner scanner = new Scanner(swegString.get(i));
            swegs[i] = new Sweg(scanner.nextInt(), scanner.nextInt());
        }

        return swegs;
    }

    static Bigmek[] createBigmek(List<String> bigmekString) {
        Bigmek[] bigmeks = new Bigmek[bigmekString.size()];
        for (int i = 0; i < bigmeks.length; i++) {
            Scanner scanner = new Scanner(bigmekString.get(i));
            bigmeks[i] = new Bigmek(scanner.nextInt(), scanner.nextInt());
        }
        return bigmeks;
    }

    // getter
    public Cube[] getCubes() {
        return cubeArray;
    }

    public Sweg[] getSwegArray() {
        return swegArray;
    }

    public Bigmek[] getBigmekArray() {
        return bigmekArray;
    }

    public AbstractEnemy[] getEnemyArray() {
        return enemyArray;
    }

    public void update(boolean scrollingLeft, boolean scrollingRight) {
        for (Cube cube : cubeArray)
            cube.updateBounding(scrollingLeft, scrollingRight);

        if (bigmekArray != null)
            for (Bigmek bigmek : bigmekArray)
                bigmek.scroll(scrollingLeft, scrollingRight);

        if (enemyArray != null)
            for (AbstractEnemy enemy : enemyArray)
                enemy.update(scrollingLeft, scrollingRight, cubeArray);

        if (swegArray != null)
            for (Sweg sweg : swegArray)
                sweg.scroll(scrollingLeft, scrollingRight);

    }

}
