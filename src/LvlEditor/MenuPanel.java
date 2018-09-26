package LvlEditor;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    private JLabel testLabel;

    public MenuPanel(Dimension requestedDimension){
        setSize(requestedDimension);

        setLayout(new FlowLayout());
        testLabel = new JLabel("testLabel");
        testLabel.setVisible(true);
        add(testLabel);

        System.out.println("MenuPanel constructor was called");
        setVisible(true);
    }
}
