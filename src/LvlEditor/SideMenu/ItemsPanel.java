package LvlEditor.SideMenu;


import map.Cube;
import map.BasicGameObject;
import map.Sweg;
import map.enemies.AbstractEnemy;
import map.enemies.Dolan;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemsPanel extends JPanel {
    private JButton cubeButton;
    private JButton enemyButton;
    private JButton swegButton;


    public ItemsPanel() {

        BasicGameObject activeObject;

        setLayout(new BoxLayout(this, 1));

        cubeButton = new JButton(Cube.class.getSimpleName());
        enemyButton = new JButton(AbstractEnemy.class.getSimpleName());
        swegButton = new JButton(Sweg.class.getSimpleName());

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
            System.out.println(this.gameObject.getClass().getSimpleName());


        }
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
