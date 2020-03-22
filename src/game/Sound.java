package game;

import javax.sound.sampled.*;
import java.io.File;


public enum Sound {
    MONEY, PLAYER_KILLED, PLAYER_DEAD, ENEMY_KILLED, BIGMEK;

    private File soundFile;
    private AudioInputStream soundStream;
    private AudioFormat soundFormat;
    private DataLine.Info info;
    private Clip ringSoundClip;

    Sound() {
        soundFile = new File("./src/resources/sound/" + name() + ".wav");
    }

    public void play() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    soundStream = AudioSystem.getAudioInputStream(soundFile);
                    soundFormat = soundStream.getFormat();
                    info = new DataLine.Info(Clip.class, soundFormat);

                    ringSoundClip = (Clip) AudioSystem.getLine(info);

                    ringSoundClip.open(soundStream);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                ringSoundClip.start();
                ringSoundClip.close();
            }
        });
    }
}
