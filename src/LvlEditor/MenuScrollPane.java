package LvlEditor;

import map.Cube;

import javax.swing.*;
import java.awt.*;

public class MenuScrollPane extends JScrollPane {
    private JPanel menuPanel;

    public MenuScrollPane(int minYSize){
        setSize(250, minYSize * Cube.size);
        menuPanel = new MenuPanel(minYSize);
        setViewportView(menuPanel);

    }







}
