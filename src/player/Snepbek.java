package player;

import helpers.Bounding;

public class Snepbek extends AbstractHat {

    public Snepbek(Bounding playerBounding) {
        super(playerBounding);
        createLook();
        createBounding(playerBounding, look);
    }

    public Snepbek(int x, int y) {
        super(x, y);
        createLook();

    }

}
