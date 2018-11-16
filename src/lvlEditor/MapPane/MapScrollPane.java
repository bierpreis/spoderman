package lvlEditor.MapPane;


import lvlEditor.SideMenu.GameObjectWrapper;
import map.Lvl;

import javax.swing.*;


public class MapScrollPane extends JScrollPane {

    private MapPanel mapPanel;

    public MapScrollPane(Lvl lvl, GameObjectWrapper objectWrapper) {
        setLayout(new ScrollPaneLayout());
        adjustSize();


        mapPanel = new MapPanel(lvl, objectWrapper);
        setViewportView(mapPanel);
        //mapPanel.setVisible(true);
        setVisible(true);
        //setPreferredSize(new Dimension(lvl.getDimension().width * Cube.getSize(), lvl.getDimension().height * Cube.getSize()));
    }

    private void adjustSize() {
        revalidate();
    }


}
