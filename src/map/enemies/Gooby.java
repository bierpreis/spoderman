package map.enemies;

import helpers.Bounding;
import map.Cube;


import javax.imageio.ImageIO;
import java.io.IOException;

public class Gooby extends AbstractEnemy {
    public Gooby(int x, int y, Cube[] cube){
        super(x,y,cube);
        try {
            this.look = ImageIO.read(getClass().getClassLoader().getResourceAsStream("img/Dolan.png"));
        }catch (IOException ioe){
            System.out.println("/img/Dolan.png not found!");
        }
        topBounding = new Bounding(x + 5, y, look.getWidth() - 10, 10);
    }
}
