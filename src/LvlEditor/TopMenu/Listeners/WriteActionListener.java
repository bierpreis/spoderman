package LvlEditor.TopMenu.Listeners;

import LvlEditor.MapFileSaveFrame;
import map.Lvl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WriteActionListener implements ActionListener {
    private Lvl lvl;


    public WriteActionListener(Lvl lvl) {
        this.lvl = lvl;

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        MapFileSaveFrame saveFrame = new MapFileSaveFrame();

        String fileName = saveFrame.getRequestedName();
        lvl.writeToFile(fileName);
    }
}

