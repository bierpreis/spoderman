package player;

import helpers.Collectable;
import map.BasicGameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;


public abstract class AbstractHat extends BasicGameObject implements Collectable {

    protected BufferedImage look;

    public AbstractHat(Rectangle playerBounding) {
        super(playerBounding.x, playerBounding.y);
        createLook();
        bounding.width = look.getWidth();
        bounding.height = look.getHeight();

    }

    public AbstractHat(int x, int y) {

        super(x, y);
        createLook();
        bounding.width = look.getWidth();
        bounding.height = look.getHeight();
    }

    @Override
    public boolean checkIfCollected(Rectangle playerBounding) {
        return (playerBounding.intersects(bounding));

    }

    public BufferedImage getLook() {
        return look;
    }

    protected void createLook() {
        try {
            this.look = ImageIO.read(getClass().getClassLoader().getResourceAsStream("img/" + this.getClass().getSimpleName().toLowerCase() + ".png"));
        } catch (Exception e) {
            System.out.println("Hat image not found!");
            e.printStackTrace();
        }

    }

    protected void createBounding(Rectangle playerBounding, BufferedImage look) {
        this.bounding = new Rectangle(playerBounding.x, playerBounding.y - 100, look.getWidth(), look.getHeight());
    }

    public void updateBounding(Rectangle playerBounding) {
        bounding.x = playerBounding.x;
        bounding.y = playerBounding.y - look.getHeight() + 10;
    }

    public String toText() {
        return bounding.y + " " + bounding.y + " " + this.getClass().getSimpleName();
    }


}
