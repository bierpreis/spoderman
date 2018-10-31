package LvlEditor.SideMenu;

import map.Cube;

import javax.swing.*;
import java.awt.*;

public class ItemsScrollPane extends JScrollPane {


    public ItemsScrollPane(int minYSize){
        //44 is a value to correct height
        setSize(250, minYSize * Cube.getSize()+44);
        ItemsPanel itemsPanel = new ItemsPanel();
        setViewportView(itemsPanel);


    }







}
