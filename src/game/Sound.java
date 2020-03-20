package game;

import javax.sound.sampled.*;
import java.io.File;


public enum Sound {
    MONEY, PLAYER_KILLED, PLAYER_DEAD, ENEMY_KILLED, BIGMEK;

    //todo: rename?
    private File ringSoundFile;
    private AudioInputStream soundStream;
    private AudioFormat soundFormat;
    private DataLine.Info info;
    private Clip ringSoundClip;

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


        //todo: this fixes problem but takes long time
        ringSoundClip.close();
    }


}
