package LvlEditor.TopMenu;


import LvlEditor.MapArea.MapScrollPane;
import LvlEditor.MapFileReader;
import LvlEditor.MapFileSaveFrame;
import LvlEditor.SizeDialog;
import map.Lvl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {

    private JButton writeFileButton;
    private JButton loadFileButton;
    private JButton newMapButton;

    private MapScrollPane mapScrollPane;


    public MenuPanel(int minYSize, Lvl lvl, MapScrollPane mapScrollPane) {
        this.mapScrollPane = mapScrollPane;
        setSize(200, minYSize);

        setLayout(new FlowLayout());


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

        newMapButton = new JButton("New Map");
        newMapButton.addActionListener(new NewMapListener(lvl, mapScrollPane));
        add(newMapButton);


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

    class NewMapListener implements ActionListener {
        private Lvl lvl;
        private MapScrollPane mapScrollPane;

        NewMapListener(Lvl lvl, MapScrollPane mapScrollPane) {
            this.lvl = lvl;
            this.mapScrollPane = mapScrollPane;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            new SizeDialog(lvl, mapScrollPane);
        }
    }


}
