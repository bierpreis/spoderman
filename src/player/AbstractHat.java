package player;

import helpers.Bounding;
import map.AbstractMapObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class AbstractHat extends AbstractMapObject {

    protected BufferedImage look;

    public AbstractHat(Bounding playerBounding) {
        createLook();
        createBounding(playerBounding);

    }


    public BufferedImage getLook() {
        return look;
    }

    private void createLook() {
        try {
            this.look = ImageIO.read(getClass().getClassLoader().getResourceAsStream("img/" + this.getClass().getSimpleName().toLowerCase() + ".png"));
        } catch (Exception e) {
            System.out.println("Hat image not found!");
            e.printStackTrace();
        }

    }

    protected void createBounding(Bounding playerBounding) {
        this.bounding = new Bounding(playerBounding.x, playerBounding.y - 100, playerBounding.width, playerBounding.height);
    }

    public void updateBounding(Bounding playerBounding) {
        bounding.x = playerBounding.x;
        bounding.y = playerBounding.y - look.getHeight() + 10;
    }


}
