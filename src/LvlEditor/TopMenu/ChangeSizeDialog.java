package LvlEditor.TopMenu;

import LvlEditor.AbstractSizeDialog;
import LvlEditor.MapArea.MapScrollPane;
import map.Lvl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeSizeDialog extends AbstractSizeDialog {

    public ChangeSizeDialog(Lvl lvl, MapScrollPane mapScrollPane) {
        super(lvl, mapScrollPane);

        setTitle("Choose new Size");

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkIfValuesOk(widthInput.getText(), heightInput.getText()))
                    lvl.changeSize(requestedWidth, requestedHeight);
                mapScrollPane.repaint();
                dispose();
            }
        });
        errorLabel = new JLabel("Values are not valid!");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        //?? what is this
    }

}
