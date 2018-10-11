package LvlEditor.MapArea;

import LvlEditor.LvlEditor;
import LvlEditor.MapArea.MapObject;
import map.Cube;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MapPanel extends JPanel {
    MapObject mapObject;

    public MapPanel(MapObject mapObject){
        this.mapObject = mapObject;
        setPreferredSize(new Dimension(mapObject.getDimension().width* Cube.getSize(), mapObject.getDimension().height*Cube.getSize()));
        setLayout(new FlowLayout());



        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                //jPanel2MousePressed(evt);
                System.out.println("mouse pressed!!!!!!!!!!!!!");
            }
            public void mouseReleased(MouseEvent evt) {
                System.out.println("mouse released");
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent evt) {
                System.out.println("mouse dragged");
            }
        });
    }


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        drawCubes(g);

    }

    private void drawCubes(Graphics g){
        g.setColor(Color.BLUE);
        for(int cubeX = 0; cubeX<mapObject.getDimension().width; cubeX++){
            for(int cubeY = 0; cubeY<mapObject.getDimension().height; cubeY++){
                Cube currentCube = mapObject.getCube(cubeX, cubeY);
                g.drawRect(currentCube.getBounding().x, currentCube.getBounding().y, currentCube.getBounding().width, currentCube.getBounding().height);
            }

        }
    }



}
