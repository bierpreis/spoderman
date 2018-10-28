package LvlEditor.SideMenu;

import map.Cube;
import map.Lvl;

import javax.swing.*;

public class MenuScrollPane extends JScrollPane {
    private JPanel menuPanel;


    public MenuScrollPane(int minYSize, Lvl lvl){
        //44 is a value to correct height
        setSize(250, minYSize * Cube.getSize()+44);
        menuPanel = new MenuPanel(minYSize, lvl);
        setViewportView(menuPanel);


    }







}
