package LvlEditor;


import javax.swing.JFileChooser;
import javax.swing.JFrame;


public class MapFileChooser extends JFrame {



    public MapFileChooser() {

        JFileChooser fileChooser = new JFileChooser();

        int rVal = fileChooser.showSaveDialog(MapFileChooser.this);
        if (rVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("name: " + fileChooser.getName());
            System.out.println(fileChooser.getSelectedFile().getName());

        }
        if (rVal == JFileChooser.CANCEL_OPTION) {

        }

    }


}
