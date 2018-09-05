package LvlEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SizeDialogue extends JDialog {
    private int requestedWidth = -1;
    private int requestedHeight = -1;

    private JLabel errorLabel;

    private boolean areValuesReady = false;

    JTextField widthInput;
    JTextField heightInput;

    public SizeDialogue() {

        setSize(270, 200);
        setLocation(300, 300);
        setTitle("Choose Size");

        setLayout(new FlowLayout());


        JPanel widthPanel = new JPanel();
        JPanel heightPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JPanel errorPanel = new JPanel();


        JLabel label = new JLabel("Choose map width:");
        widthPanel.add(label);
        widthInput = new JTextField();
        widthInput.setColumns(5);
        widthPanel.add(widthInput);

        JLabel chooseHeight = new JLabel("Choose map height:");
        heightPanel.add(chooseHeight);
        heightInput = new JTextField();
        heightPanel.add(heightInput);
        heightInput.setColumns(5);


        JButton okButton = new JButton("k");
        buttonPanel.add(okButton);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkIfValuesOk(widthInput.getText(), heightInput.getText());
            }
        });
        //buttonPanel.setBackground(Color.BLUE);

        errorLabel = new JLabel("Values are not valid!");

        errorPanel.add(errorLabel);


        add(widthPanel);
        add(heightPanel);
        add(buttonPanel);
        add(errorPanel);
        errorLabel.setVisible(false);


    }

    public Dimension askForDimension() {
        setVisible(true);

        while (requestedWidth == -1 || requestedHeight == -1) {


            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        dispose();
        return new Dimension(requestedWidth, requestedHeight);

    }

    private void checkIfValuesOk(String widthString, String heightString) {
        try {
            requestedWidth = Integer.parseInt(widthString);
            requestedHeight = Integer.parseInt(heightString);
        } catch (NumberFormatException e) {
            errorLabel.setVisible(true);
        }

    }

    public Dimension getRequestedDimension() {
        System.out.println("returned dimension: " + requestedWidth + " " + requestedHeight);
        return new Dimension(requestedWidth, requestedHeight);
    }



}
