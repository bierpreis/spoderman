package LvlEditor;

import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel {

    public MapPanel(){
        setPreferredSize(new Dimension(500,500));
        setBackground(Color.GREEN);
        setVisible(true);
    }


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.PINK);
        g.drawRect(50,50,50,50);
        System.out.println("paint was called!!");
    }

    public static void main(String[] args){
        JFrame niceFrame = new JFrame();
        JPanel mapPanel = new MapPanel();
        niceFrame.setPreferredSize(new Dimension(500,500));
        mapPanel.setVisible(true);
        niceFrame.add(mapPanel);
        niceFrame.setVisible(true);

    }
}
