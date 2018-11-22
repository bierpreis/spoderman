package lvleditor.topmenu;

import lvleditor.mappane.MapScrollPane;
import map.Lvl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SizeChangeWarning extends JDialog implements ActionListener {

    private JButton okButton;
    private MapScrollPane mapScrollPane;
    private Lvl lvl;

    public SizeChangeWarning(Lvl lvl, MapScrollPane mapScrollPane) {
        this.mapScrollPane = mapScrollPane;
        this.lvl = lvl;
        setTitle("Warning");
        setSize(800, 120);
        setLocation(250, 250);


        JPanel panel = new JPanel();
        JLabel label = new JLabel("Warning! Changing size is only possible if size gets bigger than before");
        panel.add(label);


        okButton = new JButton("k");
        getRootPane().setDefaultButton(okButton);
        okButton.addActionListener(this);
        okButton.setSize(30, 100);
        panel.add(okButton);


        add(panel);
        setModal(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == okButton) {
            new ChangeSizeDialog(lvl, mapScrollPane);
            dispose();


        }

    }
}
