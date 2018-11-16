package lvlEditor.SideMenu;

import map.BasicGameObject;

import javax.swing.*;
import java.awt.*;

public class ActiveItemPanel extends JPanel {
    private GameObjectWrapper objectWrapper;


    public ActiveItemPanel(GameObjectWrapper objectWrapper) {
        this.objectWrapper = objectWrapper;
        setPreferredSize(new Dimension(200, 80));


    }

    public void setItem(BasicGameObject gameObject) {
        objectWrapper.set(gameObject);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        objectWrapper.get().draw(g);

    }
}
