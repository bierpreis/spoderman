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

    private List<Cube> cubeList;
    private List<Sweg> swegList;
    private List<Bigmek> bigmekList;
    private List<AbstractEnemy> enemyList;

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

        cubeList = createCubes(cubeStringList);
        enemyList = createEnemies(enemyStringList);
        swegList = createSweg(swegStringList);
        bigmekList = createBigmek(bigmekStringList);

    }


    static List<Cube> createCubes(List<String> cubeStringList) {

        List<Cube> cubeList = new LinkedList<>();

        for (int i = 0; i < cubeStringList.size(); i++) {
            Scanner scanner = new Scanner(cubeStringList.get(i));
            cubeList.add(new Cube(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));
        }

        return cubeList;
    }

    static List<AbstractEnemy> createEnemies(List<String> enemyString) {
        List<AbstractEnemy> enemyList = new LinkedList<>();
        for (int i = 0; i < enemyString.size(); i++) {
            Scanner scanner = new Scanner(enemyString.get(i));
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            String type = scanner.next();
            if (type.equals("dolan"))
                enemyList.add(new Dolan(x, y));
            if (type.equals("gooby"))
                enemyList.add(new Gooby(x, y));

        }
        return enemyList;
    }

    static List<Sweg> createSweg(List<String> swegString) {
        List<Sweg> swegList = new LinkedList<>();
        for (int i = 0; i < swegString.size(); i++) {
            Scanner scanner = new Scanner(swegString.get(i));
            swegList.add(new Sweg(scanner.nextInt(), scanner.nextInt()));
        }

        return swegList;
    }

    static List<Bigmek> createBigmek(List<String> bigmekString) {
        List bigmekList = new LinkedList<Bigmek>();
        for (int i = 0; i < bigmekString.size(); i++) {
            Scanner scanner = new Scanner(bigmekString.get(i));
            bigmekList.add(new Bigmek(scanner.nextInt(), scanner.nextInt()));
        }
        return bigmekList;
    }

    // getter
    public List<Cube> getCubes() {
        return cubeList;
    }

    public List<Sweg> getSwegList() {
        return swegList;
    }

    public List<Bigmek> getBigmekList() {
        return bigmekList;
    }

    public List<AbstractEnemy> getEnemyArray() {
        return enemyList;
    }

    public void update(boolean scrollingLeft, boolean scrollingRight) {
        for (Cube cube : cubeList)
            cube.updateBounding(scrollingLeft, scrollingRight);

        for (Bigmek bigmek : bigmekList)
            bigmek.scroll(scrollingLeft, scrollingRight);

        for (AbstractEnemy enemy : enemyList)
            enemy.update(scrollingLeft, scrollingRight, cubeList);


        for (Sweg sweg : swegList)
            sweg.scroll(scrollingLeft, scrollingRight);

    }

}
