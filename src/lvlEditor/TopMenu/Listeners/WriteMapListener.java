package lvlEditor.TopMenu.Listeners;

import lvlEditor.TopMenu.MapFileSaveFrame;
import map.Lvl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WriteMapListener implements ActionListener {
    private Lvl lvl;


    public WriteMapListener(Lvl lvl) {
        this.lvl = lvl;

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        MapFileSaveFrame saveFrame = new MapFileSaveFrame();

        String fileName = saveFrame.getRequestedName();
        lvl.writeToFile(fileName);
    }
}

