package LvlEditor;

import map.Cube;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends Frame {
    private JScrollPane menuPanel;
    private JScrollPane mapPane;
    private JSplitPane splitPane;

    Cube[][] cubeArray;
    public MainFrame(Dimension requestedDimension){
        setTitle("Spoderman Lvl Editor");

        System.out.println("requested height" + requestedDimension.height);
        cubeArray = new Cube[requestedDimension.width][ requestedDimension.height];

        menuPanel = new MenuPanel(requestedDimension.height);
        mapPane = new MapPane(requestedDimension);
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,menuPanel, mapPane);

        System.out.println("size: " + mapPane.getSize());

        menuPanel.getViewport().setBackground(Color.RED);

        splitPane.setOneTouchExpandable(true);
        add(splitPane);

        splitPane.setDividerLocation(100);
        splitPane.setVisible(true);



        addWindowListener(new MainFrameListener());
        setSize(menuPanel.getWidth() + mapPane.getWidth(), menuPanel.getHeight());
        setVisible(true);
        System.out.println("map pane size: " + mapPane.size());


    }

    class MainFrameListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e){
            e.getWindow().dispose();
            System.exit(0);
        }
    }

}
