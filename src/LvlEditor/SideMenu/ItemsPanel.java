package LvlEditor.SideMenu;


import LvlEditor.Eraser;
import LvlEditor.GameObjectWrapper;
import map.*;
import map.Gold;
import player.Snepbek;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemsPanel extends JPanel {
    private JButton cubeButton;

    private JButton swegButton;

    private JButton dolanButton;
    private JButton goobyButton;

    private JButton snepbekButton;

    private JButton bigmekButton;

    private ActiveItemPanel activePanel;

    private JButton eraserButton;


    public ItemsPanel(GameObjectWrapper objectWrapper) {

        //todo: create add button method and simplyfy this


        setLayout(new BorderLayout());

        cubeButton = new JButton(Cube.class.getSimpleName());

        swegButton = new JButton(Gold.class.getSimpleName());

        dolanButton = new JButton(Dolan.class.getSimpleName());
        goobyButton = new JButton(Gooby.class.getSimpleName());

        snepbekButton = new JButton(Snepbek.class.getSimpleName());

        bigmekButton = new JButton(Bigmek.class.getSimpleName());

        eraserButton = new JButton(Eraser.class.getSimpleName());


        activePanel = new ActiveItemPanel(objectWrapper);


        JPanel itemsListPanel = new JPanel();
        itemsListPanel.setLayout(new BoxLayout(itemsListPanel, 1));


        cubeButton.addActionListener(new ItemListener(new Cube(0, 0)));

        swegButton.addActionListener(new ItemListener(new Gold(0, 0)));

        dolanButton.addActionListener(new ItemListener(new Dolan(0, 0)));
        goobyButton.addActionListener(new ItemListener(new Gooby(0, 0)));

        snepbekButton.addActionListener(new ItemListener(new Snepbek(0, 0)));

        bigmekButton.addActionListener(new ItemListener(new Bigmek(0, 0)));

        eraserButton.addActionListener(new ItemListener(new Eraser()));



        itemsListPanel.add(cubeButton);

        itemsListPanel.add(swegButton);

        itemsListPanel.add(dolanButton);
        itemsListPanel.add(goobyButton);

        itemsListPanel.add(snepbekButton);

        itemsListPanel.add(bigmekButton);

        itemsListPanel.add(eraserButton);

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
