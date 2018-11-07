package LvlEditor.MapArea;

import map.Cube;
import map.Lvl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MapPanel extends JPanel {
    Lvl lvl;

    public MapPanel(Lvl lvl) {
        this.lvl = lvl;
        setLayout(new FlowLayout());


        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                lvl.setCubeActive(evt.getX(), evt.getY());
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
        for (int cubeX = 0; cubeX < lvl.getDimension().width; cubeX++) {
            for (int cubeY = 0; cubeY < lvl.getDimension().height; cubeY++) {
                lvl.getCube(cubeX, cubeY).draw(g);

            }

        }
        setPreferredSize(new Dimension(lvl.getDimension().width * Cube.getSize(), lvl.getDimension().height * Cube.getSize()));
    }

    Dimension getActualSize() {
        return lvl.getDimension();
    }
}
