package game;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class SoundTest {

    public static void main(String[] args) {
        SoundTest soundTest = new SoundTest();
        try {
            soundTest.loadAndPlayClip();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void loadAndPlayClip() throws UnsupportedAudioFileException,
            IOException, LineUnavailableException, InterruptedException {




        while (true) {

            URL url = getClass().getResource("/resources/sound/MONEY.wav");
            AudioInputStream ais = AudioSystem.getAudioInputStream(url);
            DataLine.Info info = new DataLine.Info(Clip.class, ais.getFormat());

            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(ais);
            System.out.println("playing sound...");
            clip.start();
            Thread.sleep(1000); // Duration should match length of audio file.
            clip.close();
        }
    }
}
