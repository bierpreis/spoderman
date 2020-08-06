package game;

import javax.sound.sampled.*;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Logger;


public class Audio {


    private static final Logger LOGGER = Logger.getLogger(Game.class.getName());


    public static void play(Sound sound) {


        URL url = Audio.class.getResource("/resources/sound/" + sound.name() + ".wav");

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(url);

            DataLine.Info info = new DataLine.Info(Clip.class, ais.getFormat());

            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(ais);

            clip.start();


        } catch (Exception e) {
            LOGGER.severe(e.toString());
        }
    }
}
