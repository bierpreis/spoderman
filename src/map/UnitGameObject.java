package map;

import helpers.Config;
import player.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public abstract class UnitGameObject extends BasicGameObject {

    protected boolean onTop, onGround, onRightSide, onLeftSide;


    private int speed = 0;
    public Rectangle topBounding;
    protected Rectangle botBounding;
    private Rectangle leftBounding;
    private Rectangle rightBounding;
    protected BufferedImage look;


    public UnitGameObject(int x, int y) {
        super(x, y);
    }


    protected void createBoundings() {
        bounding = new Rectangle(bounding.x, bounding.y, look.getWidth(), look.getHeight());
        topBounding = new Rectangle(bounding.x + 5, bounding.y - 5, bounding.width - 10, bounding.height / 2);
        botBounding = new Rectangle(bounding.x + 5, bounding.y + bounding.height / 2, bounding.width - 10, bounding.height / 2);
        leftBounding = new Rectangle(bounding.x, bounding.y + 5, bounding.width / 2, bounding.height - 10);
        rightBounding = new Rectangle(bounding.x + bounding.width / 2, bounding.y + 5, bounding.width / 2, bounding.height - 10);


    }

    protected void updateHelpBoundings() {
        rightBounding.x = bounding.x + bounding.width / 2;
        rightBounding.y = bounding.y + 5;
        leftBounding.x = bounding.x;
        leftBounding.y = bounding.y + 5;
        topBounding.x = bounding.x;
        topBounding.y = bounding.y - 10;
        botBounding.x = bounding.x;
        botBounding.y = bounding.y + 20;
    }


    public BufferedImage getLook() {
        return look;
    }

    protected void gravity(Cube[][] cubeArray) {

        boolean falling = true;
        for (int cubeX = 0; cubeX < cubeArray.length; cubeX++) {
            for (int cubeY = 0; cubeY < cubeArray[0].length; cubeY++)
                if (cubeArray[cubeX][cubeY].isActive())
                    if (botBounding.intersects(cubeArray[cubeX][cubeY].getBounding())) {

                        falling = false;

                    }
        }

        if (falling) {
            bounding.y = bounding.y + Config.gravity;
        }

    }

    protected void checkCubeCollisions(Cube[][] cubeArray) {
        onRightSide = false;
        onLeftSide = false;
        onGround = false;
        onTop = false;
        for (int cubeX = 0; cubeX < cubeArray.length; cubeX++) {
            for (Cube cube : cubeArray[cubeX]) {
                if (cube.isActive()) {
                    if (leftBounding.intersects(cube.getBounding()))
                        onLeftSide = true;
                    if (rightBounding.intersects(cube.getBounding()))
                        onRightSide = true;
                    if (topBounding.intersects(cube.getBounding()))
                        onTop = true;
                    if (botBounding.intersects(cube.getBounding()))
                        onGround = true;
                }
            }
        }

    }

    public void update(Cube[][] cubeArray) {
        updateHelpBoundings();
        checkCubeCollisions(cubeArray);
        gravity(cubeArray);

    }

    @Override
    public void draw(Graphics g) {

        g.drawImage(getLook(), getX(), getY(), null);


    }

    protected void createLook() {
        try {
            look = ImageIO.read(getClass().getClassLoader().getResourceAsStream("img/" + this.getClass().getSimpleName() + ".png"));
        } catch (IOException e) {
            System.out.println("tried to get " + "img/" + this.getClass().getSimpleName() + ".png");
            e.printStackTrace();
        }
    }
}
