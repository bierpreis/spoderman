package LvlEditor;

import LvlEditor.MapArea.MapScrollPane;
import map.Lvl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AbstractSizeDialog extends JDialog implements ActionListener {
    protected int requestedWidth = 0;
    protected int requestedHeight = 0;

    protected JLabel errorLabel;

    protected JTextField widthInput;
    protected JTextField heightInput;

    protected JLabel widthLabel;
    protected JLabel heightLabel;

    protected JButton okButton;

    public AbstractSizeDialog(Lvl lvl, MapScrollPane mapScrollPane) {

        setSize(270, 200);
        setLocation(300, 300);


        setLayout(new FlowLayout());


        JPanel widthPanel = new JPanel();
        JPanel heightPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JPanel errorPanel = new JPanel();


        widthLabel = new JLabel("Choose map width:");
        widthPanel.add(widthLabel);
        widthInput = new JTextField();
        widthInput.setColumns(5);
        widthPanel.add(widthInput);

        heightLabel = new JLabel("Choose map height:");
        heightPanel.add(heightLabel);
        heightInput = new JTextField();
        heightPanel.add(heightInput);
        heightInput.setColumns(5);


        okButton = new JButton("k");
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

    protected boolean checkIfValuesOk(String widthString, String heightString) {
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
