package lvleditor.topmenu;

import lvleditor.mappane.MapScrollPane;
import map.Lvl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeSizeDialog extends JDialog implements ActionListener {
    private int requestedWidth = 0;
    private int requestedHeight = 0;

    private JLabel errorLabel;

    private JTextField widthInput;
    private JTextField heightInput;

    public ChangeSizeDialog(Lvl lvl, MapScrollPane mapScrollPane) {

        setSize(270, 200);
        setLocation(300, 300);
        setTitle("Choose new Size");

        setLayout(new FlowLayout());

        JPanel oldSizePanel = new JPanel();
        JPanel widthPanel = new JPanel();
        JPanel heightPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JPanel errorPanel = new JPanel();


        JLabel oldSizeLabel = new JLabel("Old size: " + lvl.getCubes().length + "x" + lvl.getCubes()[0].length);
        oldSizePanel.add(oldSizeLabel);

        JLabel label = new JLabel("Choose new map width:");
        widthPanel.add(label);
        widthInput = new JTextField();
        widthInput.setColumns(5);
        widthPanel.add(widthInput);

        JLabel chooseHeight = new JLabel("Choose new map height:");
        heightPanel.add(chooseHeight);
        heightInput = new JTextField();
        heightPanel.add(heightInput);
        heightInput.setColumns(5);


        JButton okButton = new JButton("k");
        buttonPanel.add(okButton);

        okButton.addActionListener(actionEvent -> {
            if (checkIfValuesAreIntegers(widthInput.getText(), heightInput.getText()))
                if (checkIfResizeIsSafe(lvl, requestedWidth, requestedHeight))
                    lvl.changeSize(requestedWidth, requestedHeight);
            mapScrollPane.repaint();
            dispose();
        });

        errorLabel = new JLabel("Values are not valid!");

        errorPanel.add(errorLabel);

        add(oldSizePanel);
        add(widthPanel);
        add(heightPanel);
        add(buttonPanel);
        add(errorPanel);
        errorLabel.setVisible(false);

        getRootPane().setDefaultButton(okButton);

        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent ae) {
        //?? what is this
    }

    private boolean checkIfValuesAreIntegers(String widthString, String heightString) {
        try {
            requestedWidth = Integer.parseInt(widthString);
            requestedHeight = Integer.parseInt(heightString);
        } catch (NumberFormatException e) {
            errorLabel.setVisible(true);
        }

        return (requestedHeight > 0 && requestedWidth > 0);


    }

    private boolean checkIfResizeIsSafe(Lvl lvl, int newX, int newY) {
        int oldX = lvl.getCubes().length;
        int oldY = lvl.getCubes()[0].length;
        if (oldX <= newX && oldY <= newY)
            return true;
        errorLabel.setVisible(true);
        return false;

    }


}
