package LvlEditor;

import map.BasicGameObject;
import map.Cube;

import java.awt.*;

public class Eraser extends BasicGameObject {

    public Eraser() {
        super(0, 0);
    }


    @Override

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(bounding.x, bounding.y, Cube.getSize(), Cube.getSize());

    }
}
