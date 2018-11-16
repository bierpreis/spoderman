package graphics;

public class FpsCounter {

    private int fps = 0;
    private int fpsCounter = 0;
    private long timeUntilLastSecond = System.currentTimeMillis() + 1000;


    int calcFps() {
        fpsCounter++;
        if (System.currentTimeMillis() > timeUntilLastSecond) {
            timeUntilLastSecond = System.currentTimeMillis() + 1000;
            fps = fpsCounter;
            fpsCounter = 0;
        }
        return fps;

    }
}
