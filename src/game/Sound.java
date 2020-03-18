package game;

import javax.sound.sampled.*;
import java.io.File;


public enum Sound {
    MONEY, PLAYER_KILLED, PLAYER_DEAD, ENEMY_KILLED, BIGMEK;

    //todo: all private

    //todo: rename?
    File ringSoundFile;
    AudioInputStream soundStream;
    AudioFormat soundFormat;
    DataLine.Info info;
    Clip ringSoundClip;

    Sound() {

        ringSoundFile = new File("./src/resources/sound/" + name() + ".wav");
    }

    public void play() {
        try {
            soundStream = AudioSystem.getAudioInputStream(ringSoundFile);
            soundFormat = soundStream.getFormat();
            info = new DataLine.Info(Clip.class, soundFormat);

            ringSoundClip = (Clip) AudioSystem.getLine(info);

            //todo: this triggeres exception. maybe on the second use? maybe close correctly?
            ringSoundClip.open(soundStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ringSoundClip.start();
    }


}
