package LvlEditor.TopMenu;


import LvlEditor.MapArea.MapScrollPane;
import LvlEditor.TopMenu.Listeners.ChangeSizeListener;
import LvlEditor.TopMenu.Listeners.LoadMapListener;
import LvlEditor.TopMenu.Listeners.NewMapListener;
import LvlEditor.TopMenu.Listeners.WriteMapListener;
import map.Lvl;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    private JButton writeFileButton;
    private JButton loadFileButton;
    private JButton newMapButton;
    private JButton changeSizeButton;

    private MapScrollPane mapScrollPane;


    public MenuPanel(int minYSize, Lvl lvl, MapScrollPane mapScrollPane) {
        this.mapScrollPane = mapScrollPane;
        setSize(200, minYSize);

        setLayout(new FlowLayout());


        writeFileButton = new JButton("Write to File");
        writeFileButton.addActionListener(new WriteMapListener(lvl));

        newMapButton = new JButton("New Map");
        newMapButton.addActionListener(new NewMapListener(lvl, mapScrollPane));

        changeSizeButton = new JButton("Change Map Size");
        changeSizeButton.addActionListener(new ChangeSizeListener(lvl, mapScrollPane));


        add(newMapButton);


        add(writeFileButton);


        loadFileButton = new JButton("Load File");
        loadFileButton.addActionListener(new LoadMapListener(lvl, mapScrollPane));
        add(loadFileButton);

        add(changeSizeButton);


        setVisible(true);
    }


}
