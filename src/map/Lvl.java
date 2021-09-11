package map;

import game.Game;
import lvleditor.topmenu.FileAlreadyExistsDialog;

import java.awt.*;
import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lvl {

    private static final Logger LOGGER = Logger.getLogger(Game.class.getName());

    private volatile Cube[][] cubeArray;

    private List<UnitGameObject> gameObjectList;

    private boolean isBigmekCollected = false;

    public Lvl(int lvlNumber) {

        gameObjectList = new LinkedList<>();
        String pathToFile = "lvl" + Integer.toString(lvlNumber) + ".txt";
        FileReader fr;
        try {
            fr = new FileReader(pathToFile);
        } catch (FileNotFoundException e) {
            LOGGER.severe("could not find lvl file");
            return;
        }

        BufferedReader br = new BufferedReader(fr);
        List<String> cubeStringList = readFile(br, Cube.class);

        cubeArray = createCubes(cubeStringList);

    }

    public boolean isBigmekCollected() {
        return isBigmekCollected;
    }

    public Lvl(String pathToFile) {
        init(10, 10);
        gameObjectList = new LinkedList<>();

        try {
            readLvlFile(pathToFile);
        } catch (Exception e) {
            LOGGER.severe(e.toString());
        }
    }

    public void createFromFile(String fileName) {
        gameObjectList = new LinkedList<>();

        try {
            readLvlFile(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Lvl() {
        init(10, 10);
        gameObjectList = new LinkedList<>();


    }


    public void init(int requestedXSize, int requestedYSize) {
        cubeArray = createEmptyCubes(requestedXSize, requestedYSize);
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

    public Cube[][] getCubes() {
        return cubeArray;
    }

    public Cube getCube(int cubeX, int cubeY) {
        return cubeArray[cubeX][cubeY];
    }

    public void updateGameObjects() {
        gameObjectList.forEach(gameObject -> gameObject.update(cubeArray));
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
            bw.write(Integer.toString(cubeArray.length) + " ");
            bw.write(Integer.toString(cubeArray[0].length));
            bw.write("\n");

            bw.write("BEGIN_CUBES\n");
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
        try {
            bw.write("BEGIN_UNITS\n");
            for (BasicGameObject gameObject : gameObjectList)
                bw.write(gameObject.toText() + "\n");

            bw.write("END_UNITS");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    private Cube[][] createEmptyCubes(int cubeX, int cubeY) {
        cubeArray = new Cube[cubeX][cubeY];

        for (int currentX = 0; currentX < cubeX; currentX++) {
            for (int currentY = 0; currentY < cubeY; currentY++) {
                cubeArray[currentX][currentY] = new Cube(currentX, currentY);
            }

        }
        return cubeArray;
    }

    public void setCubeActive(int xPixel, int yPixel) {
        int cubeXNumber = xPixel / Cube.getSize();
        int cubeYNumber = yPixel / Cube.getSize();
        if (cubeArray.length > cubeXNumber && cubeArray[0].length > cubeYNumber)
            cubeArray[cubeXNumber][cubeYNumber].setActive();
    }

    public void setBigmekCollected() {
        this.isBigmekCollected = true;
    }

    private void readLvlFile(String fileName) throws Exception {

        FileReader fr = null;
        try {
            fr = new FileReader("./src/resources/map/" + fileName + ".txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedReader br = new BufferedReader(fr);

        Scanner scanner = new Scanner(br.readLine());
        init(scanner.nextInt(), scanner.nextInt());

        if (br.readLine().equals("BEGIN_CUBES"))
            LOGGER.fine("reading cubes");
        String nextLine = br.readLine();
        while (!nextLine.equals("END_CUBES")) {
            scanner = new Scanner(nextLine);
            int cubeX = scanner.nextInt() / Cube.getSize();
            int cubeY = scanner.nextInt() / Cube.getSize();
            cubeArray[cubeX][cubeY].setActive();
            nextLine = br.readLine();
        }

        if (br.readLine().equals("BEGIN_UNITS"))
            LOGGER.fine("reading unit objects");

        nextLine = br.readLine();
        while (!nextLine.equals("END_UNITS")) {
            gameObjectList.add(createObjectFromString(nextLine));
            nextLine = br.readLine();
        }
        LOGGER.info("finished creating map");
    }

    private UnitGameObject createObjectFromString(String originalString) {
        String objectPatternString = "^[A-Z][a-z]+";


        Pattern objectPattern = Pattern.compile(objectPatternString);
        Matcher objectMatcher = objectPattern.matcher(originalString);

        String objectString = "";


        while (objectMatcher.find()) {
            objectString = objectMatcher.group(0);
        }

        String numberString = originalString.replaceAll(objectPatternString, "");

        Scanner scanner = new Scanner(numberString);

        int x = scanner.nextInt();
        int y = scanner.nextInt();

        UnitGameObject gameObject = null;

        try {
            gameObject = (UnitGameObject) Class.forName("map." + objectString).getConstructors()[0].newInstance(x, y);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gameObject;
    }

    public void changeSize(int requestedXSize, int requestedYSize) {
        Cube[][] oldCubeArray = cubeArray;
        cubeArray = createEmptyCubes(requestedXSize, requestedYSize);

        for (int oldCubeX = 0; oldCubeX < oldCubeArray.length; oldCubeX++)
            for (int oldCubeY = 0; oldCubeY < oldCubeArray[0].length; oldCubeY++) {
                if (oldCubeArray[oldCubeX][oldCubeY].isActive())
                    cubeArray[oldCubeX][oldCubeY].setActive();
            }
    }

    public void eraseGameObjects(int x, int y) {
        Rectangle eraserBounding = new Rectangle(x, y, 1, 1);
        for (Cube[] cubes : cubeArray) {
            for (Cube cube : cubes) {
                if (eraserBounding.intersects(cube.getBounding()))
                    cube.setInActive();

            }
        }

        gameObjectList.removeIf(gameObject -> eraserBounding.intersects(gameObject.bounding));
    }


}
