package LvlEditor.SideMenu;


import javax.swing.*;
import java.awt.*;


public class ItemsScrollPane extends JScrollPane {


    public ItemsScrollPane() {

        setSize(new Dimension(250,500));
        ItemsPanel itemsPanel = new ItemsPanel();
        setViewportView(itemsPanel);

        System.out.println("size;" + getSize());

    }


}
