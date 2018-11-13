package LvlEditor.TopMenu.Listeners;

import LvlEditor.MapArea.MapScrollPane;
import LvlEditor.TopMenu.CreateSizeDialog;
import map.Lvl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewMapListener implements ActionListener {
    private Lvl lvl;
    private MapScrollPane mapScrollPane;

    public NewMapListener(Lvl lvl, MapScrollPane mapScrollPane) {
        this.lvl = lvl;
        this.mapScrollPane = mapScrollPane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new CreateSizeDialog(lvl, mapScrollPane);
    }
}