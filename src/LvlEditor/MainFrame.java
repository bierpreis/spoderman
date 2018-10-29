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
    private JScrollPane mapScrollPane;
    private JSplitPane splitPane;
    private JPanel menuPanel;

    private Lvl lvl;

    public MainFrame() {
        SizeDialog sizeDialogue = new SizeDialog();

        Dimension requestedDimension = sizeDialogue.askForDimension();
        setTitle("Spoderman Lvl Editor");

        setLayout(new BorderLayout());


        lvl = new Lvl();
        lvl.init(requestedDimension);

        menuPanel = new MenuPanel(500, lvl);
        add(menuPanel, BorderLayout.NORTH);


        itemsScrollPane = new ItemsScrollPane(requestedDimension.height);


        mapScrollPane = new MapScrollPane(lvl);
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
