package lvlEditor.TopMenu.Listeners;

import lvlEditor.MapPane.MapScrollPane;
import lvlEditor.TopMenu.SizeChangeWarning;
import map.Lvl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeSizeListener implements ActionListener {
    private Lvl lvl;
    private MapScrollPane mapPane;

    public ChangeSizeListener(Lvl lvl, MapScrollPane mapPane) {
        this.lvl = lvl;
        this.mapPane = mapPane;
    }

    @Override

    public void actionPerformed(ActionEvent e) {
        new SizeChangeWarning(lvl, mapPane);

    }
}