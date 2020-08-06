package game;

import javax.sound.sampled.*;
import java.io.File;
import java.util.HashMap;
import java.util.logging.Logger;


public class Audio {

    private final static HashMap<Sound, File> soundMap;

    private static final Logger LOGGER = Logger.getLogger(Game.class.getName());


    static {
        soundMap = new HashMap<>();
        for (Sound sound : Sound.values()) {
            try {
                soundMap.put(sound, new File("./src/resources/sound/" + sound.name() + ".wav"));
            } catch (NullPointerException e) {
                LOGGER.warning("NPE Outting " + sound + " and "
                        + new File("./src/resources/sound/" + sound.name() + ".wav") + " into map ");
                LOGGER.warning(e.toString());
            }

        }
    }

    public static void play(Sound sound) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                LOGGER.fine("playing sound " + sound.name());
                AudioInputStream soundStream;
                AudioFormat soundFormat;
                DataLine.Info info;
                Clip soundClip;

                try {
                    soundStream = AudioSystem.getAudioInputStream(soundMap.get(sound));
                    soundFormat = soundStream.getFormat();
                    info = new DataLine.Info(Clip.class, soundFormat);

                    soundClip = (Clip) AudioSystem.getLine(info);

                    soundClip.open(soundStream);


                    soundClip.start();
                    soundClip.close();

                } catch (Exception e) {
                    LOGGER.severe(e.toString());
                }


            }
        }).start();

    }
}
