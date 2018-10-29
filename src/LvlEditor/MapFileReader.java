package LvlEditor;

import map.Lvl;

import javax.swing.*;

public class MapFileReader extends JFileChooser {

    String fileToLoad;

    public MapFileReader(Lvl lvl) {
        JFileChooser fileChooser = new JFileChooser();

        int rVal = fileChooser.showSaveDialog(MapFileReader.this);
        if (rVal == JFileChooser.APPROVE_OPTION) {

            fileToLoad = fileChooser.getSelectedFile().getName();

        }
        if (rVal == JFileChooser.CANCEL_OPTION) {

        }

    }

    public String getRequestedName() {
        while (fileToLoad == null) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return fileToLoad;
    }
}
