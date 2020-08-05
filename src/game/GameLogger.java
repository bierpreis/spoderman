package game;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class GameLogger {

    private static final Logger LOGGER = Logger.getLogger(Game.class.getName());

    static void init() {

        //format logging to fit time and message in one line
        System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tF %1$tT %4$s %2$s %5$s%6$s%n");

        Handler handler = null;
        try {
            handler = new FileHandler("log.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        handler.setFormatter(new SimpleFormatter());
        LOGGER.addHandler(handler);


    }
}
