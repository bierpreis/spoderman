package LvlEditor;

import LvlEditor.MapArea.MapPanel;
import LvlEditor.MapArea.MapScrollPane;
import map.Lvl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SizeDialog extends JDialog implements ActionListener {
    private int requestedWidth = 0;
    private int requestedHeight = 0;

    private JLabel errorLabel;

    JTextField widthInput;
    JTextField heightInput;

    public SizeDialog(Lvl lvl, MapScrollPane mapScrollPane) {

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
                if (checkIfValuesOk(widthInput.getText(), heightInput.getText())) ;
                lvl.init(new Dimension(requestedWidth, requestedHeight));
                mapScrollPane.repaint();
                dispose();
            }
        });

        errorLabel = new JLabel("Values are not valid!");

        errorPanel.add(errorLabel);


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

    private boolean checkIfValuesOk(String widthString, String heightString) {
        try {
            requestedWidth = Integer.parseInt(widthString);
            requestedHeight = Integer.parseInt(heightString);
        } catch (NumberFormatException e) {
            errorLabel.setVisible(true);
        }

        if (requestedHeight > 0 && requestedWidth > 0)
            return true;
        else return false;

    }

    public Dimension getRequestedDimension() {
        return new Dimension(requestedWidth, requestedHeight);
    }


}
