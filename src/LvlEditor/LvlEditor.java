package LvlEditor;

import java.awt.*;

public class LvlEditor {

    public static void main(String args[]) {
        SizeDialogue sizeDialogue = new SizeDialogue();
        sizeDialogue.askForDimension();

        MainFrame mainFrame = new MainFrame(sizeDialogue.getRequestedDimension());


    }
}
