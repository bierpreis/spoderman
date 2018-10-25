package LvlEditor.MapArea;

import map.Cube;
import map.Lvl;

import javax.swing.*;
import java.awt.*;

public class MapScrollPane extends JScrollPane {

    private JPanel mapPanel;

    public MapScrollPane(Lvl lvl){
        setLayout(new ScrollPaneLayout());
        setSize(new Dimension (lvl.getDimension().width* Cube.getSize()+32,lvl.getDimension().height*Cube.getSize()));




        mapPanel = new MapPanel(lvl);
        setViewportView(mapPanel);
        mapPanel.setVisible(true);
        setVisible(true);
    }


}
