package map.enemies;

import java.awt.*;

public class Dolan extends AbstractEnemy {
    public Dolan(int x, int y){
        super(x,y);

        createLook();
        topBounding = new Rectangle(x + 5, y, look.getWidth() - 10, 10);

    }
}
