package LvlEditor;

import map.Cube;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends Frame {
    private JScrollPane menuPanel = new MenuPanel();
    private JScrollPane mapPane = new MapPanel();
    private JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,menuPanel, mapPane);

    Cube[][] cubeArray;
    public MainFrame(Dimension requestedDimension){
        setTitle("Spoderman Lvl Editor");


        cubeArray = new Cube[requestedDimension.width][ requestedDimension.height];

        mapPane.setSize(requestedDimension.width*Cube.size,requestedDimension.height*Cube.size);
        //mapPane.getViewport().setBackground(Color.BLUE);
        mapPane.setVisible(true);
        System.out.println("size: " + mapPane.getSize());

        menuPanel.setSize(250, mapPane.getHeight());
        menuPanel.getViewport().setBackground(Color.RED);

        splitPane.setOneTouchExpandable(true);
        add(splitPane);

        splitPane.setDividerLocation(150);
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
