package mainmenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;


class WrongCodeDialog extends JDialog implements ActionListener {
    private JButton buttonOk;
    private WrongCodeDialog wrong;

    void showWrongCode() {
        wrong = new WrongCodeDialog();
        wrong.setTitle("lol ronk");
        wrong.setSize(300, 150);
        wrong.setLocation(250, 250);

        JPanel panel = new JPanel();
        JLabel label = new JLabel("lol coed is ronk");
        panel.add(label);
        panel.setLocation(580, 280);

        buttonOk = new JButton("  k  ");
        panel.add(buttonOk);
        buttonOk.addActionListener(this);

        buttonOk.setVisible(true);
        wrong.getRootPane().setDefaultButton(buttonOk);
        wrong.add(panel);

        wrong.setModal(true);
        wrong.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == buttonOk) {
            wrong.dispose();
        }

    }
}