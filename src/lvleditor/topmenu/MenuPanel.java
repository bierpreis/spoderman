package lvleditor.topmenu;


import lvleditor.mappane.MapScrollPane;
import lvleditor.topmenu.Listeners.ChangeSizeListener;
import lvleditor.topmenu.Listeners.LoadMapListener;
import lvleditor.topmenu.Listeners.NewMapListener;
import lvleditor.topmenu.Listeners.WriteMapListener;
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
