package LvlEditor.MapArea;

import map.Cube;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MapPanel extends JPanel {
    MapObject mapObject;

    public MapPanel(MapObject mapObject) {
        this.mapObject = mapObject;
        setPreferredSize(new Dimension(mapObject.getDimension().width * Cube.getSize(), mapObject.getDimension().height * Cube.getSize()));
        setLayout(new FlowLayout());


        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                mapObject.setCubeActive(evt.getX(), evt.getY());


            }

            public void mouseReleased(MouseEvent evt) {
                repaint();

            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent evt) {
            }
        });

    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawCubes(g);

    }

    private void drawCubes(Graphics g) {
        for (int cubeX = 0; cubeX < mapObject.getDimension().width; cubeX++) {
            for (int cubeY = 0; cubeY < mapObject.getDimension().height; cubeY++) {
                mapObject.getCube(cubeX, cubeY).draw(g);

            }

        }
    }

}
