package LvlEditor.SideMenu;

import LvlEditor.LvlEditor;
import LvlEditor.SideMenu.MenuPanel;
import map.Cube;

import javax.swing.*;

public class MenuScrollPane extends JScrollPane {
    private JPanel menuPanel;

    public MenuScrollPane(int minYSize){
        //44 is a value to correct height
        setSize(250, minYSize * Cube.getSize()+44);
        menuPanel = new MenuPanel(minYSize);
        setViewportView(menuPanel);

    }







}
