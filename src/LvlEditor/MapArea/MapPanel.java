package LvlEditor.MapArea;

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
        adjustSize(lvl);
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

    private void adjustSize(Lvl lvl) {
        //todo
        if (lvl.getDimension() == null)
            setSize(500, 500);
        else setSize(lvl.getDimension());
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
    }

    public void resize(){
        resize(lvl.getDimension());
    }

}
