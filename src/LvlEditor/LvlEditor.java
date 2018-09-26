package LvlEditor;

import java.awt.*;

public class LvlEditor {

    public static void main(String args[]) {
        SizeDialogue sizeDialogue = new SizeDialogue();


        MainFrame mainFrame = new MainFrame(sizeDialogue.askForDimension());


    }
}
