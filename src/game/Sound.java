package game;

import javax.sound.sampled.*;
import java.io.File;


public enum Sound {
    MONEY, PLAYER_DEAD;

    File ringSoundFile;
    AudioInputStream soundStream;
    AudioFormat soundFormat;
    DataLine.Info info;
    Clip ringSoundClip;

    Sound() {

        ringSoundFile = new File("sound/" + name() + ".wav");
    }

    public void play() {
        try {
            soundStream = AudioSystem.getAudioInputStream(ringSoundFile);
            soundFormat = soundStream.getFormat();
            info = new DataLine.Info(Clip.class, soundFormat);

            ringSoundClip = (Clip) AudioSystem.getLine(info);
            ringSoundClip.open(soundStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ringSoundClip.start();
    }


}
