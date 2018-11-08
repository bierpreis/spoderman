package LvlEditor.SideMenu;

import map.BasicGameObject;
import map.Cube;

import javax.swing.*;
import java.awt.*;

public class ActiveItemPanel extends JPanel {
    private BasicGameObject activeObject;


    public ActiveItemPanel() {
        activeObject = new Cube(getWidth()/2, 0);
        setPreferredSize(new Dimension(200,80));


    }

    public void setItem(BasicGameObject gameObject) {
        this.activeObject = gameObject;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        activeObject.draw(g);
        System.out.println("active size" + getSize());

    }
}
