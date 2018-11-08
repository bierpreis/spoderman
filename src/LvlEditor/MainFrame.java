package LvlEditor;

import LvlEditor.MapArea.MapScrollPane;
import LvlEditor.SideMenu.ItemsScrollPane;
import LvlEditor.TopMenu.MenuPanel;
import map.Cube;
import map.Lvl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends Frame {
    private JScrollPane itemsScrollPane;
    private MapScrollPane mapScrollPane;
    private JSplitPane splitPane;
    private JPanel menuPanel;

    private Lvl lvl;

    public MainFrame() {

        GameObjectWrapper objectWrapper = new GameObjectWrapper();
        objectWrapper.set(new Cube(0, 0));

        setTitle("Spoderman Lvl Editor");

        setLayout(new BorderLayout());

        lvl = new Lvl();

        mapScrollPane = new MapScrollPane(lvl, objectWrapper);

        menuPanel = new MenuPanel(200, lvl, mapScrollPane);
        add(menuPanel, BorderLayout.NORTH);

        itemsScrollPane = new ItemsScrollPane(objectWrapper);


        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, itemsScrollPane, mapScrollPane);

        splitPane.setOneTouchExpandable(true);
        add(splitPane);

        splitPane.setDividerLocation(itemsScrollPane.getWidth());

        //todo (increase?!)
        calculatePreferredSize(lvl);

        addWindowListener(new MainFrameListener());

        pack();
        setVisible(true);


    }

    private void calculatePreferredSize(Lvl lvl) {
        int minXSize = 600;
        int minYSize = 400;

        int mapXSize = lvl.getDimension().width * Cube.getSize();
        int mapYSize = lvl.getDimension().height * Cube.getSize();

        int requestedXSize, requestedYSize;
        if (mapXSize > minXSize)
            requestedXSize = mapXSize;
        else requestedXSize = minXSize;

        if (mapXSize > minXSize)
            requestedYSize = mapYSize;
        else requestedYSize = minYSize;
        setPreferredSize(new Dimension(requestedXSize, requestedYSize));
    }

    class MainFrameListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            e.getWindow().dispose();
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new MainFrame();
    }

}
