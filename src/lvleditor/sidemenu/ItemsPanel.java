package lvleditor.sidemenu;


import lvleditor.mappane.Eraser;
import map.*;
import map.Gold;
import map.Snepbek;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemsPanel extends JPanel {


    private ActiveItemPanel activePanel;


    public ItemsPanel(GameObjectWrapper objectWrapper) {

        setLayout(new BorderLayout());

        JPanel itemsListPanel = new JPanel();
        itemsListPanel.setLayout(new BoxLayout(itemsListPanel, 1));

        activePanel = new ActiveItemPanel(objectWrapper);


        addButton(Cube.class, itemsListPanel);
        addButton(Gold.class, itemsListPanel);
        addButton(Dolan.class, itemsListPanel);
        addButton(Gooby.class, itemsListPanel);
        addButton(Snepbek.class, itemsListPanel);
        addButton(Bigmek.class, itemsListPanel);
        addButton(Eraser.class, itemsListPanel);

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

    private void addButton(Class<? extends BasicGameObject> itemClass, JPanel itemsPanel) {
        JButton itemButton = new JButton(itemClass.getSimpleName());
        try {
            itemButton.addActionListener(new ItemListener((BasicGameObject) itemClass.getConstructors()[0].newInstance(0, 0)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        itemsPanel.add(itemButton);
    }


}
