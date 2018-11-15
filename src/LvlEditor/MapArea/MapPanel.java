package LvlEditor.MapArea;

import LvlEditor.Eraser;
import LvlEditor.GameObjectWrapper;
import map.BasicGameObject;
import map.Cube;
import map.Lvl;
import map.UnitGameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.lang.reflect.Constructor;

public class MapPanel extends JPanel {
    Lvl lvl;

    public MapPanel(Lvl lvl, GameObjectWrapper objectWrapper) {
        this.lvl = lvl;
        setLayout(new FlowLayout());

        //todo own class for mouse adapter
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                if ((objectWrapper.get() instanceof UnitGameObject)) {
                    try {
                        Constructor[] constructors = objectWrapper.get().getClass().getConstructors();
                        BasicGameObject newObject = (BasicGameObject) constructors[0].newInstance(evt.getX(), evt.getY());

                        lvl.addGameObject(newObject);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (objectWrapper.get() instanceof Cube) {
                    lvl.setCubeActive(evt.getX(), evt.getY());
                }
                if (objectWrapper.get() instanceof Eraser) {
                    lvl.eraseGameObjects(evt.getX(), evt.getY());

                }

            }

            public void mouseReleased(MouseEvent evt) {
                repaint();
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent evt) {
                if (objectWrapper.get() instanceof Cube) {
                    lvl.setCubeActive(evt.getX(), evt.getY());
                }
                repaint();
            }

        });

    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawCubes(g);
        drawUnits(g);
    }

    private void drawUnits(Graphics g) {
        for (UnitGameObject gameObject : lvl.getGameObjectList()) {
            gameObject.draw(g);
        }
    }

    private void drawCubes(Graphics g) {
        for (int cubeX = 0; cubeX < lvl.getCubes().length; cubeX++) {
            for (int cubeY = 0; cubeY < lvl.getCubes()[0].length; cubeY++) {
                lvl.getCube(cubeX, cubeY).draw(g);

            }

        }
        setPreferredSize(new Dimension(lvl.getCubes().length * Cube.getSize(), lvl.getCubes()[0].length * Cube.getSize()));
        revalidate();
    }
}
