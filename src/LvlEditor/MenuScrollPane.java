package LvlEditor;

import map.Cube;

import javax.swing.*;
import java.awt.*;

public class MenuScrollPane extends JScrollPane {
    private JPanel menuPanel;

    public MenuScrollPane(Dimension requestedDimension){
        setSize(250, requestedDimension.height* Cube.size);
        menuPanel = new MenuPanel(requestedDimension);
        setViewportView(menuPanel);

    }







}
