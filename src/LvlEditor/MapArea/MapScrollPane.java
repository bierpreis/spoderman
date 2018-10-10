package LvlEditor.MapArea;

import LvlEditor.LvlEditor;
import LvlEditor.MapArea.MapObject;
import LvlEditor.MapArea.MapPanel;
import map.Cube;

import javax.swing.*;
import java.awt.*;

public class MapScrollPane extends JScrollPane {

    private JPanel mapPanel;

    public MapScrollPane(MapObject mapObject){
        setLayout(new ScrollPaneLayout());
        setSize(new Dimension (mapObject.getDimension().width* Cube.getSize(),mapObject.getDimension().height*Cube.getSize()));



        mapPanel = new MapPanel(mapObject);
        setViewportView(mapPanel);
        mapPanel.setVisible(true);
        setVisible(true);
    }


}
