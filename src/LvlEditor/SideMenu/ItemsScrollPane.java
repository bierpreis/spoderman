package LvlEditor.SideMenu;


import LvlEditor.GameObjectWrapper;

import javax.swing.*;
import java.awt.*;


public class ItemsScrollPane extends JScrollPane {


    public ItemsScrollPane(GameObjectWrapper objectWrapper) {

        setSize(new Dimension(250,500));
        ItemsPanel itemsPanel = new ItemsPanel(objectWrapper);
        setViewportView(itemsPanel);

        System.out.println("size;" + getSize());

    }


}
