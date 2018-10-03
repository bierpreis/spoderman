package LvlEditor;

import map.Cube;

import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel {
    MapObject mapObject;

    public MapPanel(MapObject mapObject){
        this.mapObject = mapObject;
        setPreferredSize(new Dimension(mapObject.getDimension().width* Cube.size, mapObject.getDimension().height*Cube.size));
        setLayout(new FlowLayout());
    }


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.BLUE);

    }

    private void DrawCubes(Graphics g){
        g.setColor(Color.BLUE);
        for(int cubeX = 0; cubeX<mapObject.getDimension().width; cubeX++){
            for(int cubeY = 0; cubeY<mapObject.getDimension().height; cubeY++){
                Cube currentCube = mapObject.getCube(cubeX, cubeY);
                g.drawRect(currentCube.g);
            }

        }
    }

}
