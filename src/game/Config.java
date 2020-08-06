package game;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class Config {
    private static final Logger LOGGER = Logger.getLogger(Game.class.getName());
    private static String pathToProperties = "./src/resources/config.properties";

    private static Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(pathToProperties));
        } catch (IOException e) {
            LOGGER.severe(e.toString());
        }
    }


    public static String get(String propertyName) {
        return properties.getProperty(propertyName);


    }

}
