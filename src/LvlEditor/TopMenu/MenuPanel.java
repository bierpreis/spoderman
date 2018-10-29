package LvlEditor.TopMenu;


import LvlEditor.MapFileReader;
import LvlEditor.MapFileSaveFrame;
import map.Lvl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {
    private JLabel testLabel;
    private JButton writeFileButton;
    private JButton loadFileButton;


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
                MapFileSaveFrame saveFrame = new MapFileSaveFrame();

                String fileName = saveFrame.getRequestedName();
                lvl.writeToFile(fileName);
            }
        });


        add(writeFileButton);


        loadFileButton = new JButton("Load File");
        loadFileButton.addActionListener(new LoadActionListener(lvl));
        add(loadFileButton);


        setVisible(true);
    }

    class LoadActionListener implements ActionListener {
        private Lvl lvl;

        LoadActionListener(Lvl lvl) {
            this.lvl = lvl;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new MapFileReader(lvl);
            String fileName = fileChooser.getSelectedFile().getName();
            this.lvl = new Lvl(Integer.parseInt(fileName));

        }
    }


}
