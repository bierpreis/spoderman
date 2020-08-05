package lvleditor.sidemenu;


import javax.swing.*;
import java.awt.*;


public class ItemsScrollPane extends JScrollPane {


    public ItemsScrollPane(GameObjectWrapper objectWrapper) {

        setSize(new Dimension(250,500));
        ItemsPanel itemsPanel = new ItemsPanel(objectWrapper);
        setViewportView(itemsPanel);


    }


}
