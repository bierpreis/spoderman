package LvlEditor.TopMenu;


import LvlEditor.MapArea.MapScrollPane;
import LvlEditor.MapFileSaveFrame;
import LvlEditor.SizeDialog;
import LvlEditor.TopMenu.Listeners.LoadActionListener;
import LvlEditor.TopMenu.Listeners.NewMapListener;
import LvlEditor.TopMenu.Listeners.WriteActionListener;
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
        writeFileButton.addActionListener(new WriteActionListener(lvl));

        newMapButton = new JButton("New Map");
        newMapButton.addActionListener(new NewMapListener(lvl, mapScrollPane));
        add(newMapButton);


        add(writeFileButton);


        loadFileButton = new JButton("Load File");
        loadFileButton.addActionListener(new LoadActionListener(lvl, mapScrollPane));
        add(loadFileButton);


        setVisible(true);
    }


}
