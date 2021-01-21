package com.company.GUI;

import com.company.RSA;
import com.company.RSAKey;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.PlainDocument;
import java.awt.*;

public class GenerateKeysDialog extends JDialog {

    private final JTextField keyNameTextField = new JTextField(20);
    private final JTextField bitLengthTextField = new JTextField(20);
    private boolean succeeded;
    private RSAKey rsaKey;
    Frame parent;
    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints gridBagConstraints = new GridBagConstraints();

    public GenerateKeysDialog(Frame parent) {
        super(parent, "Generate Keys", true);
        this.parent = parent;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        addComponent(new JLabel("Key name: "), 0, 0, 1);
        addComponent(keyNameTextField, 1, 0, 2);
        addComponent(new JLabel("Bit length: "), 0, 1, 1);
        addComponent(this.bitLengthTextField, 1, 1, 2);
        enableBitLengthIntFilter();

        panel.setBorder(new LineBorder(Color.GRAY));

        JButton generateButton = new JButton("Generate");
        generateButton.addActionListener(e -> generateButtonAction());

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(generateButton);
        buttonPanel.add(cancelButton);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.PAGE_END);

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    private void addComponent(JComponent component, int gridX, int gridY, int gridWidth) {
        gridBagConstraints.gridx = gridX;
        gridBagConstraints.gridy = gridY;
        gridBagConstraints.gridwidth = gridWidth;
        panel.add(component, gridBagConstraints);
    }

    private void enableBitLengthIntFilter(){
        PlainDocument doc = (PlainDocument) this.bitLengthTextField.getDocument();
        doc.setDocumentFilter(new IntFilter());
    }

    private void generateButtonAction(){
        System.out.println("generate");
        generateKeys();
        dispose();
    }

    private void generateKeys() {
        try {
            String keyName = getKeyName();
            int bitLength = getBitLength();
            RSA.generateKeys(keyName, bitLength);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(parent, "Not valid bit length","Bit Length Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e){
            JOptionPane.showMessageDialog(parent, e.getMessage(),"Bit Length Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String getKeyName() throws  IllegalArgumentException{
        String keyName = keyNameTextField.getText().trim();
        if (keyName.length() == 0){
            throw new IllegalArgumentException("Key name empty");
        }
        return keyNameTextField.getText().trim();
    }

    private int getBitLength() throws NumberFormatException {
        return Integer.parseInt(bitLengthTextField.getText());
    }

    public boolean isSucceeded() {
        return succeeded;
    }
}