package game;

import javax.sound.sampled.*;
import java.io.File;
import java.util.HashMap;


public class Audio {

    private static HashMap<Sound, File> soundMap;


    static {
        soundMap = new HashMap<>();
        for (Sound sound : Sound.values()) {
            //todo: null pointer here!
            try {
                soundMap.put(sound, new File("./src/resources/sound/" + sound.name() + ".wav"));
            }catch(NullPointerException e){
                System.out.println("NPE Outting " + sound + " and "
                        + new File("./src/resources/sound/" + sound.name() + ".wav") + " into map ");
                e.printStackTrace();
            }

        }
    }

    public static void play(Sound sound) {

        new Thread(new Runnable() {
            @Override
            public void run() {
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
                    e.printStackTrace();
                }
            }
        });
    }
}
