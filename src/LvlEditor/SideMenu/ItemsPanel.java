package LvlEditor.SideMenu;


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


    public ItemsPanel() {


        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        cubeButton = new JButton(Cube.class.getSimpleName());
        enemyButton = new JButton(AbstractEnemy.class.getSimpleName());
        swegButton = new JButton(Sweg.class.getSimpleName());

        activePanel = new ActiveItemPanel();
        add(activePanel);


        cubeButton.addActionListener(new ItemListener(new Cube(1, 1)));
        enemyButton.addActionListener(new ItemListener(new Dolan(1, 1)));
        swegButton.addActionListener(new ItemListener(new Sweg(1, 1)));

        add(cubeButton);
        add(enemyButton);
        add(swegButton);


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
            activePanel.repaint();


        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        activePanel.draw(g);
    }

    public int getMinWidth() {
        int minWidth = 0;
        if (cubeButton.getWidth() > minWidth)
            minWidth = cubeButton.getWidth();

        if (enemyButton.getWidth() > minWidth)
            minWidth = enemyButton.getWidth();

        if (enemyButton.getWidth() > minWidth)
            minWidth = enemyButton.getWidth();

        return minWidth;
    }


}
