package LvlEditor;

import map.Cube;

import javax.swing.*;
import java.awt.*;

public class MapScrollPane extends JScrollPane {

    private JPanel mapPanel;

    public MapScrollPane(Dimension requestedDimension){
        setLayout(new ScrollPaneLayout());
        setSize(requestedDimension.width* Cube.size,requestedDimension.height*Cube.size);
        //getViewport().setBackground(Color.BLUE);


        mapPanel = new MapPanel(requestedDimension);
        setViewportView(mapPanel);
        mapPanel.setVisible(true);
        setVisible(true);
    }


}
