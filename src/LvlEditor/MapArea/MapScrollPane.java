package LvlEditor.MapArea;


import map.Lvl;

import javax.swing.*;


public class MapScrollPane extends JScrollPane {

    private MapPanel mapPanel;

    public MapScrollPane(Lvl lvl) {
        setLayout(new ScrollPaneLayout());
        adjustSize(lvl);


        mapPanel = new MapPanel(lvl);
        setViewportView(mapPanel);
        //mapPanel.setVisible(true);
        setVisible(true);

    }

    private void adjustSize(Lvl lvl) {
        setPreferredSize(lvl.getDimension());
    }

    public void resize(){
        mapPanel.resize();
    }


}
