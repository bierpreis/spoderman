package LvlEditor.SideMenu;


import LvlEditor.GameObjectWrapper;
import map.Cube;
import map.BasicGameObject;
import map.Sweg;
import map.enemies.AbstractEnemy;
import map.enemies.Dolan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemsPanel extends JPanel {
    private JButton cubeButton;
    private JButton enemyButton;
    private JButton swegButton;

    private ActiveItemPanel activePanel;


    public ItemsPanel(GameObjectWrapper objectWrapper) {


        setLayout(new BorderLayout());

        cubeButton = new JButton(Cube.class.getSimpleName());
        enemyButton = new JButton(AbstractEnemy.class.getSimpleName());
        swegButton = new JButton(Sweg.class.getSimpleName());

        activePanel = new ActiveItemPanel(objectWrapper);

        JPanel itemsListPanel = new JPanel();
        itemsListPanel.setLayout(new BoxLayout(itemsListPanel, 1));


        cubeButton.addActionListener(new ItemListener(new Cube(0, 0)));
        enemyButton.addActionListener(new ItemListener(new Dolan(0, 0)));
        swegButton.addActionListener(new ItemListener(new Sweg(0, 0)));

        itemsListPanel.add(cubeButton);
        itemsListPanel.add(enemyButton);
        itemsListPanel.add(swegButton);

        add(activePanel, BorderLayout.NORTH);
        add(itemsListPanel, BorderLayout.CENTER);


        setVisible(true);
    }

    class ItemListener implements ActionListener {
        private BasicGameObject gameObject;

        ItemListener(BasicGameObject gameObject) {
            this.gameObject = gameObject;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            activePanel.setItem(gameObject);

            repaint();


        }
    }


}
