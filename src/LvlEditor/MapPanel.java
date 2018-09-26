package LvlEditor;

import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel {

    public MapPanel(Dimension requestedDimension){
        setPreferredSize(requestedDimension);
        setLayout(new FlowLayout());
        System.out.println("mapPanel constructor was called");
    }


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.PINK);
        g.fillRect(50,50,50,50);
        System.out.println("mapPanel paint was called");
    }

}
