package LvlEditor.Example;

import LvlEditor.LvlEditor;

import javax.swing.*;
import java.awt.*;

public class PanelExample {

    public static void main(String[] args) {

        // Erzeugung eines neuen Dialoges
        JFrame meinJDialog = new JFrame();
        meinJDialog.setTitle("JFrameBeispiel");
        meinJDialog.setSize(300, 300);

        JPanel panel = new MyPanel();
        // Hier setzen wir die Hintergrundfarbe unseres JPanels auf rot
        panel.setBackground(Color.red);
        // Hier fügen wir unserem Dialog unser JPanel hinzu
        meinJDialog.add(panel);
        // Wir lassen unseren Dialog anzeigen
        meinJDialog.setVisible(true);
    }



}
