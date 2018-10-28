package LvlEditor;

import LvlEditor.MapArea.MapScrollPane;
import LvlEditor.SideMenu.MenuScrollPane;
import map.Lvl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends Frame {
    private JScrollPane menuPanel;
    private JScrollPane mapScrollPane;
    private JSplitPane splitPane;

    private Lvl lvl;

    public MainFrame(Dimension requestedDimension) {
        setTitle("Spoderman Lvl Editor");

        lvl = new Lvl(requestedDimension);

        menuPanel = new MenuScrollPane(requestedDimension.height, lvl);
        mapScrollPane = new MapScrollPane(lvl);
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, menuPanel, mapScrollPane);

        splitPane.setOneTouchExpandable(true);
        add(splitPane);

        splitPane.setDividerLocation(menuPanel.getWidth());


        addWindowListener(new MainFrameListener());
        setSize(menuPanel.getWidth() + mapScrollPane.getWidth(), menuPanel.getHeight());
        setVisible(true);


    }

    class MainFrameListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            e.getWindow().dispose();
            System.exit(0);
        }
    }

}
