package game;

import javax.sound.sampled.*;
import java.io.File;


public enum Sound {
    MONEY, PLAYER_KILLED, PLAYER_DEAD, ENEMY_KILLED, BIGMEK;

    //todo: rename? make stuff local. change?
    private File ringSoundFile;
    private AudioInputStream soundStream;
    private AudioFormat soundFormat;
    private DataLine.Info info;
    private Clip ringSoundClip;

    Sound() {

        ringSoundFile = new File("./src/resources/sound/" + name() + ".wav");
    }

    public void play() {
        long startTime = 0;
        try {
            soundStream = AudioSystem.getAudioInputStream(ringSoundFile);
            soundFormat = soundStream.getFormat();
            info = new DataLine.Info(Clip.class, soundFormat);

            ringSoundClip = (Clip) AudioSystem.getLine(info);

            startTime = System.currentTimeMillis();

            //todo: this line takes all the time!!
            ringSoundClip.open(soundStream);

        } catch (Exception e) {
            e.printStackTrace();
        }


        ringSoundClip.start();



        ringSoundClip.close();
        System.out.println("time: " + (System.currentTimeMillis()-startTime));
    }


}
