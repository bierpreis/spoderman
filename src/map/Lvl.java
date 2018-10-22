package map;

import LvlEditor.FileAlreadyExistsWindow;
import map.enemies.Dolan;
import map.enemies.AbstractEnemy;
import map.enemies.Gooby;
import player.AbstractHat;
import player.Snepbek;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Lvl {

    private List cubeList;
    private List swegList;
    private List bigmekList;
    private List enemyList;
    private List hatList;

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
        List<String> cubeStringList = readFile(br, Cube.class);
        List<String> enemyStringList = readFile(br, AbstractEnemy.class);
        List<String> swegStringList = readFile(br, Sweg.class);
        List<String> bigmekStringList = readFile(br, Bigmek.class);
        List<String> hatStringList = readFile(br, AbstractHat.class);


        cubeList = createCubes(cubeStringList);
        enemyList = createEnemies(enemyStringList);
        swegList = createSweg(swegStringList);
        bigmekList = createBigmek(bigmekStringList);
        hatList = createHats(hatStringList);

    }

    private List<AbstractHat> createHats(List<String> hatStringList) {
        List<AbstractHat> hatList = new LinkedList<>();
        for (int i = 0; i < hatStringList.size(); i++) {
            Scanner scanner = new Scanner(hatStringList.get(i));
            int x = scanner.nextInt();
            int y = scanner.nextInt();

            String type = scanner.next();
            if (type.equals("Snepbek"))
                hatList.add(new Snepbek(x, y));
        }

        return hatList;
    }

    private List<String> readFile(BufferedReader reader, Class<? extends Readable> classType) {
        List<String> listFromFile = new LinkedList<>();

        try {
            String newLine = reader.readLine();
            String className = classType.getSimpleName();
            while (!newLine.equals("END_" + className)) {
                listFromFile.add(newLine);
                newLine = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listFromFile;
    }


    private static List<Cube> createCubes(List<String> cubeStringList) {

        List<Cube> cubeList = new LinkedList<>();

        for (int i = 0; i < cubeStringList.size(); i++) {
            Scanner scanner = new Scanner(cubeStringList.get(i));
            cubeList.add(new Cube(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));
        }

        return cubeList;
    }

    public List<AbstractHat> getHatList() {
        return hatList;
    }

    private static List<AbstractEnemy> createEnemies(List<String> enemyString) {
        List<AbstractEnemy> enemyList = new LinkedList<>();
        for (int i = 0; i < enemyString.size(); i++) {
            Scanner scanner = new Scanner(enemyString.get(i));
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            String type = scanner.next();
            if (type.equals("Dolan"))
                enemyList.add(new Dolan(x, y));
            if (type.equals("Gooby"))
                enemyList.add(new Gooby(x, y));

        }
        return enemyList;
    }

    private static List<Sweg> createSweg(List<String> swegString) {
        List<Sweg> swegList = new LinkedList<>();
        for (int i = 0; i < swegString.size(); i++) {
            Scanner scanner = new Scanner(swegString.get(i));
            swegList.add(new Sweg(scanner.nextInt(), scanner.nextInt()));
        }

        return swegList;
    }

    private static List<Bigmek> createBigmek(List<String> bigmekString) {
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

    public List<AbstractEnemy> getEnemyList() {
        return enemyList;
    }

    public void updateEnemies() {
        for (Object enemy : enemyList) {
            ((AbstractEnemy) enemy).update(cubeList);
        }
    }

    public void writeToFile(String filenName) {

        String pathToFile = filenName + ".txt";

        File f = new File(pathToFile);
        if (f.exists() && !f.isDirectory()) {
            new FileAlreadyExistsWindow(filenName);
            return;
        }
        FileWriter fw = null;
        BufferedWriter bw;
        try {
            fw = new FileWriter(pathToFile);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        bw = new BufferedWriter(fw);


        writeGameObjects(bw, cubeList);
        writeGameObjects(bw, enemyList);
        writeGameObjects(bw, swegList);
        writeGameObjects(bw, bigmekList);
        writeGameObjects(bw, hatList);

        try {
            bw.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void writeGameObjects(BufferedWriter bw, List<Readable> gameObjectList) {
        for (Readable readable : gameObjectList)
            try {
                bw.write(readable.toText() + "\n");
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

        if (!gameObjectList.get(0).getClass().getSuperclass().getSimpleName().contains("Abstract")) {
            //System.out.println(gameObjectList.get(0).getClass().getSuperclass().getSimpleName());
            try {
                bw.write("END_" + gameObjectList.get(0).getClass().getSimpleName() + "\n");
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        } else try {
            bw.write("END_" + gameObjectList.get(0).getClass().getSuperclass().getSimpleName() + "\n");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }


    }

    public static void main(String[] args) {
        Lvl lvl = new Lvl(1);
        lvl.writeToFile("newFile");
    }


}
