package LvlEditor.SideMenu;


import LvlEditor.MapFileChooser;
import map.Lvl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {
    private JLabel testLabel;
    private JButton writeFileButton;


    public MenuPanel(int minYSize, Lvl lvl) {
        setSize(200, minYSize);

        setLayout(new FlowLayout());

        testLabel = new JLabel("testLabel");
        testLabel.setVisible(true);
        add(testLabel);

        writeFileButton = new JButton("Write to File");
        writeFileButton.setVisible(true);
        writeFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MapFileChooser fileChooser = new MapFileChooser();
                String fileName = fileChooser.getRequestedName();
                System.out.println(fileName);
                lvl.writeToFile(fileName);

            }
        });
        add(writeFileButton);


        setVisible(true);
    }
}
