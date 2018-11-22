package lvleditor.topmenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FileAlreadyExistsDialog extends JDialog implements ActionListener {
    private JButton buttonOk;

    public FileAlreadyExistsDialog(String existingFileName){
        setTitle("Error");
        setSize(300, 150);
        setLocation(250, 250);

        JPanel panel = new JPanel();
        JLabel label = new JLabel("<html>The specified File " + existingFileName + " already exists. <br> Choose a new one or delete the old.</html>");
        panel.add(label);
        panel.setLocation(580, 280);

        buttonOk = new JButton("  k  ");
        panel.add(buttonOk);
        buttonOk.addActionListener(this);

        buttonOk.setVisible(true);
        getRootPane().setDefaultButton(buttonOk);
        add(panel);

        setModal(true);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == buttonOk) {
            dispose();
        }

    }
}
