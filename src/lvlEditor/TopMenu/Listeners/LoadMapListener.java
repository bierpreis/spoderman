package lvlEditor.TopMenu.Listeners;

import map.Lvl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadMapListener implements ActionListener {
    private Lvl lvl;
    JScrollPane mapScrollPane;


    public LoadMapListener(Lvl lvl, JScrollPane mapScrollPane) {
        this.lvl = lvl;
        this.mapScrollPane = mapScrollPane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            lvl.createFromFile(fileChooser.getSelectedFile().getName());
            mapScrollPane.repaint();
        }
    }
}