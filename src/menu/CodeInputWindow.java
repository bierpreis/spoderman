package menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class CodeInputWindow extends JDialog implements ActionListener {

    private final JDialog input;
    private final JButton buttonOK;
    private final JTextField textInput;
    private int unlockedLvl = 0;

    CodeInputWindow() {
        setLayout(new BorderLayout());

        input = new JDialog();

        input.setTitle("lvl coed");
        input.setSize(300, 150);
        input.setLocation(250, 250);

        JPanel panel = new JPanel();

        JLabel label = new JLabel("giff coed");
        panel.add(label);

        textInput = new InputField(15);
        textInput.setBackground(Color.YELLOW);
        panel.add(textInput);

        buttonOK = new JButton("  k  ");
        panel.add(buttonOK);

        buttonOK.addActionListener(this);
        input.getRootPane().setDefaultButton(buttonOK);

        buttonOK.setVisible(true);

        input.setModal(true);
        input.add(panel);
        input.setVisible(true);

        setFocusable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == buttonOK) {

            if (textInput.getText().equals("lol")) {

                input.dispose();

                unlockedLvl = 2;

            } else {
                input.dispose();
                wrongCode();

            }
        }
    }

    private void wrongCode() {
        WrongCodeWindow wrongCode = new WrongCodeWindow();
        wrongCode.showWrongCode();
        dispose();
    }

    public int getUnlockedLvl() {
        return unlockedLvl;
    }

}
