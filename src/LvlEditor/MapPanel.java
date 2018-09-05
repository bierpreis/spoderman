package LvlEditor;

import javax.swing.*;
import java.awt.*;

public class MapPanel extends JScrollPane {


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.GREEN);
        g.fillRect(100, 100, 200, 200);
    }
}
