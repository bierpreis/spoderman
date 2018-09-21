package LvlEditor.Example;

import LvlEditor.LvlEditor;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(10, 10, 50, 50);

    }
}
