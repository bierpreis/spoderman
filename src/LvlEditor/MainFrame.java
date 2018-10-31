package LvlEditor;

import LvlEditor.MapArea.MapScrollPane;
import LvlEditor.SideMenu.ItemsScrollPane;
import LvlEditor.TopMenu.MenuPanel;
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


        setTitle("Spoderman Lvl Editor");

        setLayout(new BorderLayout());

        lvl = new Lvl();

        mapScrollPane = new MapScrollPane(lvl);

        menuPanel = new MenuPanel(500, lvl, mapScrollPane);
        add(menuPanel, BorderLayout.NORTH);

        //todo
        itemsScrollPane = new ItemsScrollPane(500);



        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, itemsScrollPane, mapScrollPane);

        splitPane.setOneTouchExpandable(true);
        add(splitPane);

        splitPane.setDividerLocation(itemsScrollPane.getWidth());


        addWindowListener(new MainFrameListener());
        setSize(itemsScrollPane.getWidth() + mapScrollPane.getWidth(), itemsScrollPane.getHeight());
        setVisible(true);


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
