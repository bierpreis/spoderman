package LvlEditor;

import map.Cube;

import javax.swing.*;
import java.awt.*;

public class MapScrollPane extends JScrollPane {

    private JPanel mapPanel;

    public MapScrollPane(MapObject mapObject){
        setLayout(new ScrollPaneLayout());
        setSize(new Dimension (mapObject.getDimension().width* Cube.size,mapObject.getDimension().height*Cube.size));



        mapPanel = new MapPanel(mapObject);
        setViewportView(mapPanel);
        mapPanel.setVisible(true);
        setVisible(true);
    }


}
