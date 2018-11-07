package LvlEditor.SideMenu;

import map.Cube;

import javax.swing.*;
import java.awt.*;

public class ItemsScrollPane extends JScrollPane {


    public ItemsScrollPane(int minYSize){
        //44 is a value to correct height
        setSize(250, 100);
        ItemsPanel itemsPanel = new ItemsPanel();
        setViewportView(itemsPanel);



    }










}
