package LvlEditor.SideMenu;

import map.BasicGameObject;
import map.Cube;

import javax.swing.*;
import java.awt.*;

public class ActiveItemPanel extends JPanel {
    private BasicGameObject activeObject;


    public ActiveItemPanel() {
        activeObject = new Cube(0, 0);

    }

    public void setItem(BasicGameObject gameObject) {
        this.activeObject = gameObject;
    }

    public void draw(Graphics g) {
        activeObject.draw(g);
        repaint();
        System.out.println("activepanel draw" + activeObject.getClass().getSimpleName());
    }
}
