package lvleditor.topmenu.listeners;

import lvleditor.mappane.MapScrollPane;
import lvleditor.topmenu.CreateSizeDialog;
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