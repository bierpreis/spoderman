package LvlEditor.MapArea;


import map.Cube;
import map.Lvl;

import javax.swing.*;
import java.awt.*;


public class MapScrollPane extends JScrollPane {

    private MapPanel mapPanel;

    public MapScrollPane(Lvl lvl) {
        setLayout(new ScrollPaneLayout());
        adjustSize(lvl);


        mapPanel = new MapPanel(lvl);
        setViewportView(mapPanel);
        //mapPanel.setVisible(true);
        setVisible(true);
        setPreferredSize(new Dimension(lvl.getDimension().width * Cube.getSize(), lvl.getDimension().height * Cube.getSize()));
    }

    private void adjustSize(Lvl lvl) {
        setPreferredSize(lvl.getDimension());
    }


}
