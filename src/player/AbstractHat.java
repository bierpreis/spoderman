package player;

import helpers.Bounding;
import helpers.Collectable;
import map.AbstractMapObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;


public class AbstractHat extends AbstractMapObject implements Collectable {

    protected BufferedImage look;

    public AbstractHat(Bounding playerBounding) {
        super(playerBounding.x,playerBounding.y);
        createLook();
        bounding.width = look.getWidth();
        bounding.height = look.getHeight();

    }

    public AbstractHat(int x, int y){

        super(x,y);
        createLook();
        bounding.width = look.getWidth();
        bounding.height = look.getHeight();
    }

    @Override
    public boolean checkIfCollected(Bounding playerBounding) {
        if(playerBounding.intersects(bounding)) {
            return true;
        }
        else return false;
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

    protected void createBounding(Bounding playerBounding, BufferedImage look) {
        this.bounding = new Bounding(playerBounding.x, playerBounding.y - 100, look.getWidth(), look.getHeight());
    }

    public void updateBounding(Bounding playerBounding) {
        bounding.x = playerBounding.x;
        bounding.y = playerBounding.y - look.getHeight() + 10;
    }


}
