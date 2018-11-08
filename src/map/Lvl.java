package map;

import LvlEditor.FileAlreadyExistsDialog;
import map.enemies.Dolan;
import map.enemies.AbstractEnemy;
import map.enemies.Gooby;
import player.AbstractHat;
import player.Snepbek;

import java.awt.*;
import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Lvl {

    private Cube[][] cubeArray;

    private List<UnitGameObject> gameObjectList;

    private Dimension dimension;

    private boolean isBigmekCollected = false;

    public Lvl(int lvlNumber) {

        gameObjectList = new LinkedList<>();
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


        cubeArray = createCubes(cubeStringList);

    }

    public boolean isBigmekCollected() {
        return isBigmekCollected;
    }

    public Lvl() {
        //todo
        dimension = new Dimension(30, 10);
        init(dimension);
        gameObjectList = new LinkedList<>();
    }


    public void init(Dimension requestedDimension) {
        this.dimension = requestedDimension;
        cubeArray = createEmptyCubes(requestedDimension);
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

    public List<UnitGameObject> getGameObjectList() {
        return gameObjectList;
    }

    private List<String> readFile(BufferedReader reader, Class<? extends BasicGameObject> classType) {
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

    public void addGameObject(BasicGameObject newGameObject) {
        if (newGameObject instanceof UnitGameObject)
            gameObjectList.add((UnitGameObject) newGameObject);


    }


    private Cube[][] createCubes(List<String> cubeStringList) {
        Scanner scanner = new Scanner(cubeStringList.get(0));


        cubeArray = new Cube[scanner.nextInt()][scanner.nextInt()];


        for (int i = 1; i <= cubeStringList.size(); i++) {
            scanner = new Scanner(cubeStringList.get(i));
            int cubeX = scanner.nextInt();
            int cubeY = scanner.nextInt();
            cubeArray[cubeX][cubeY].setActive();
        }

        return cubeArray;
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
    public Cube[][] getCubes() {
        return cubeArray;
    }

    public Cube getCube(int cubeX, int cubeY) {
        return cubeArray[cubeX][cubeY];
    }


    public void updateGameObjects() {
        for (UnitGameObject gameObject : gameObjectList) {
            gameObject.update(cubeArray); //cubes and gameobjects here
        }
    }

    public void writeToFile(String filenName) {

        String pathToFile = filenName + ".txt";

        File f = new File(pathToFile);
        if (f.exists() && !f.isDirectory()) {
            new FileAlreadyExistsDialog(filenName);
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


        writeCubes(bw, cubeArray);
        writeGameObjects(bw, gameObjectList);

        try {
            bw.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void writeCubes(BufferedWriter bw, Cube[][] cubeArray) {
        try {
            bw.write(Integer.toString(cubeArray[0].length) + " ");
            bw.write(Integer.toString(cubeArray.length));
            bw.write("\n");


            for (int cubeX = 0; cubeX < cubeArray.length; cubeX++) {
                for (int cubeY = 0; cubeY < cubeArray[0].length; cubeY++) {
                    if (cubeArray[cubeX][cubeY].isActive()) {
                        bw.write(cubeArray[cubeX][cubeY].toText());
                        bw.write("\n");
                    }
                }

            }

            bw.write("END_CUBES\n");


        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    private void writeGameObjects(BufferedWriter bw, List<UnitGameObject> gameObjectList) {

        for (BasicGameObject gameObject : gameObjectList)
            try {
                bw.write(gameObject.toText() + "\n");
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }


    }

    public Dimension getDimension() {
        return dimension;
    }

    private Cube[][] createEmptyCubes(Dimension requestedDimension) {
        cubeArray = new Cube[requestedDimension.width][requestedDimension.height];

        for (int cubeX = 0; cubeX < requestedDimension.width; cubeX++) {
            for (int cubeY = 0; cubeY < requestedDimension.height; cubeY++) {
                cubeArray[cubeX][cubeY] = new Cube(cubeX, cubeY);
            }

        }
        return cubeArray;
    }

    public static void main(String[] args) {
        Lvl lvl = new Lvl(1);
        lvl.writeToFile("newFile");
    }

    public void setCubeActive(int xPixel, int yPixel) {
        int cubeXNumber = xPixel / Cube.getSize();
        int cubeYNumber = yPixel / Cube.getSize();
        if (cubeArray.length > cubeXNumber && cubeArray[0].length > cubeYNumber)
            cubeArray[cubeXNumber][cubeYNumber].setActive();
    }

    //todo is this rly needed or can this stay in player?
    public void setBigmekCollected() {
        this.isBigmekCollected = true;
    }


}
