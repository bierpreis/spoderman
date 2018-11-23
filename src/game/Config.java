package game;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static String pathToProperties = "./src/resources/config.properties";

    private static Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(pathToProperties));
        } catch (IOException e) {
            e.printStackTrace();
        }

       // System.out.println("property: " + Integer.parseInt(properties.getProperty("playerMoveSpeed")));
    }

    public static final int playerMoveSpeed = 5;
    public static final int playerRespawnTime = 2000;
    public static final int targetFps = 60;

    public static final int gravity = 5;

    public static final int messageBaseTime = 1000;
    public static final int messageTimePerLetter = 50;

    public static final int screenX = 800;
    public static final int screenY = 600;

    public static final int timeJumpingUp = 250;
    public static final int minTimeBetweenJumps = 600;
    public static final int escTime = 2000;
    public static final int enemyMoveSpeed = 1;
    public static final int buttonLockTime = 500;
    public static final int jumpSpeed = 18;

    public static final int windowX = 200;
    public static final int windowY = 200;


    public static final int msPerFrame = 1000 / targetFps;


    public static String get(String propertyName){
        return properties.getProperty(propertyName);


    }

}
