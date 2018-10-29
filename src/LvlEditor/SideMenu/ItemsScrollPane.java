package LvlEditor.SideMenu;

import LvlEditor.TopMenu.MenuPanel;
import map.Cube;
import map.Lvl;

import javax.swing.*;

public class ItemsScrollPane extends JScrollPane {
    private JPanel menuPanel;


    public ItemsScrollPane(int minYSize, Lvl lvl){
        //44 is a value to correct height
        setSize(250, minYSize * Cube.getSize()+44);
        ItemsPanel itemsPanel = new ItemsPanel(minYSize, lvl);
        setViewportView(itemsPanel);


    }







}
