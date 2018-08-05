package map.enemies;

import java.awt.*;

public class Gooby extends AbstractEnemy {
    public Gooby(int x, int y) {
        super(x, y);


        createLook();
        topBounding = new Rectangle(x + 5, y, look.getWidth() - 10, 10);
    }
}
