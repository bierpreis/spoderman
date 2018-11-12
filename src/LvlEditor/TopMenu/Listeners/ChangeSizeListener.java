package LvlEditor.TopMenu.Listeners;

import LvlEditor.TopMenu.SizeChangeWarning;
import map.Lvl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeSizeListener implements ActionListener {
    private Lvl lvl;
    private JScrollPane mapPane;

    public ChangeSizeListener(Lvl lvl, JScrollPane mapPane) {
        this.lvl = lvl;
        this.mapPane = mapPane;
    }

    @Override

    public void actionPerformed(ActionEvent e) {
        new SizeChangeWarning();

    }
}
