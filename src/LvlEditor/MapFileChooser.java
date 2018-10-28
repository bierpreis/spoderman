package LvlEditor;


import javax.swing.JFileChooser;
import javax.swing.JFrame;


public class MapFileChooser extends JFrame {

    String requestedName = null;


    public MapFileChooser() {

        JFileChooser fileChooser = new JFileChooser();

        int rVal = fileChooser.showSaveDialog(MapFileChooser.this);
        if (rVal == JFileChooser.APPROVE_OPTION) {

            requestedName = fileChooser.getSelectedFile().getName();

        }
        if (rVal == JFileChooser.CANCEL_OPTION) {

        }

    }

    public String getRequestedName() {
        while (requestedName == null) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return requestedName;
    }


}
