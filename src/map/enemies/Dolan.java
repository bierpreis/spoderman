package map.enemies;

import helpers.Bounding;
import map.Cube;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Dolan extends AbstractEnemy {
    public Dolan(int x, int y){
        super(x,y);

        createLook();
        topBounding = new Bounding(x + 5, y, look.getWidth() - 10, 10);

    }
}
