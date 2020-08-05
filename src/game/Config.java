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
    }



    public static String get(String propertyName){
        return properties.getProperty(propertyName);


    }

}
