package LvlEditor.MapArea;

import map.Cube;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MapPanel extends JPanel {
    MapObject mapObject;
    private Cube currentCube;

    public MapPanel(MapObject mapObject) {
        this.mapObject = mapObject;
        setPreferredSize(new Dimension(mapObject.getDimension().width * Cube.getSize(), mapObject.getDimension().height * Cube.getSize()));
        setLayout(new FlowLayout());


        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                mapObject.setCubeActive(evt.getX(), evt.getY());
            }

            public void mouseReleased(MouseEvent evt) {
                System.out.println("mouse released on: " + evt.getPoint());
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent evt) {
                System.out.println("mouse dragged");
            }
        });
    }

    private void startActivateCube(Point point) {


    }

    private void finishActivateCube(Point point) {

    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        drawCubes(g);

    }

    private void drawCubes(Graphics g) {
        g.setColor(Color.BLUE);
        for (int cubeX = 0; cubeX < mapObject.getDimension().width; cubeX++) {
            for (int cubeY = 0; cubeY < mapObject.getDimension().height; cubeY++) {
                mapObject.getCube(cubeX, cubeY).draw(g);
            }

        }
    }


}
