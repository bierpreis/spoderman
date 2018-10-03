package LvlEditor;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    private JLabel testLabel;

    public MenuPanel(int minYSize){
        setSize(200, minYSize);

        setLayout(new FlowLayout());
        testLabel = new JLabel("testLabel");
        testLabel.setVisible(true);
        add(testLabel);

        System.out.println("MenuPanel constructor was called");
        setVisible(true);
    }
}
