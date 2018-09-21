package LvlEditor;

import LvlEditor.Example.MyPanel;
import map.Cube;

import javax.swing.*;
import java.awt.*;

public class MapPane extends JScrollPane {

    private JPanel nicePanel;

    public MapPane (Dimension requestedDimension){

        setSize(requestedDimension.width* Cube.size,requestedDimension.height*Cube.size);
        getViewport().setBackground(Color.BLUE);
        setVisible(true);

        nicePanel = new MapPanel();
        add(nicePanel);
        nicePanel.setVisible(true);
    }


}
