package LvlEditor.SideMenu;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {
    private JLabel testLabel;
    private JButton writeFileButton;

    public MenuPanel(int minYSize) {
        setSize(200, minYSize);

        setLayout(new FlowLayout());

        testLabel = new JLabel("testLabel");
        testLabel.setVisible(true);
        add(testLabel);

        writeFileButton = new JButton("Write to File");
        writeFileButton.setVisible(true);
        writeFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        add(writeFileButton);


        setVisible(true);
    }
}
