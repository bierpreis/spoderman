package LvlEditor;

import map.Cube;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends Frame {
    private JScrollPane menuPanel;
    private JScrollPane mapScrollPane;
    private JSplitPane splitPane;

    Cube[][] cubeArray;
    public MainFrame(Dimension requestedDimension){
        setTitle("Spoderman Lvl Editor");

        cubeArray = new Cube[requestedDimension.width][ requestedDimension.height];

        menuPanel = new MenuScrollPane(requestedDimension);
        mapScrollPane = new MapScrollPane(requestedDimension);
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,menuPanel, mapScrollPane);

        System.out.println("size: " + mapScrollPane.getSize());

        //menuPanel.getViewport().setBackground(Color.RED);

        splitPane.setOneTouchExpandable(true);
        add(splitPane);

        splitPane.setDividerLocation(menuPanel.getWidth());




        addWindowListener(new MainFrameListener());
        setSize(menuPanel.getWidth() + mapScrollPane.getWidth(), menuPanel.getHeight());
        setVisible(true);
        System.out.println("map pane size: " + mapScrollPane.size());


    }

    class MainFrameListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e){
            e.getWindow().dispose();
            System.exit(0);
        }
    }

}
