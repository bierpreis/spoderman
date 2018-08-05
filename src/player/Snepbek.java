package player;

import java.awt.*;

public class Snepbek extends AbstractHat {

    public Snepbek(Rectangle playerBounding) {
        super(playerBounding);
        createLook();
        createBounding(playerBounding, look);
    }

    public Snepbek(int x, int y) {
        super(x, y);
        createLook();

    }

}
