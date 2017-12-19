package map.enemies;

import helpers.Bounding;
import map.Cube;


import javax.imageio.ImageIO;
import java.io.IOException;

public class Gooby extends AbstractEnemy {
    public Gooby(int x, int y){
        super(x,y);
        try {
            this.look = ImageIO.read(getClass().getClassLoader().getResourceAsStream("img/gooby.png"));
        }catch (Exception e){
            System.out.println("/img/Gooby.png not found!");
        }
        width = look.getWidth();
        height = look.getHeight();
        topBounding = new Bounding(x + 5, y, look.getWidth() - 10, 10);
    }
}
