package com.company.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIFrame extends JFrame {

    final JFrame frame = new JFrame("RSA");
    final JButton generateKeysButton = new JButton("Generate keys");
    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints gridBagConstraints = new GridBagConstraints();

    public GUIFrame() {
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;


        JButton generateButton = new JButton("Generate");
        generateButton.addActionListener(e -> {
            GenerateKeysDialog generateKeysDialog = new GenerateKeysDialog(frame);
            generateKeysDialog.setVisible(true);
        });

        JButton cancelButton = new JButton("Load key");
        cancelButton.addActionListener(e -> {});

        addComponent(generateButton, 0, 0, 1);
        addComponent(cancelButton, 1, 0, 2);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(generateButton);
        buttonPanel.add(cancelButton);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.PAGE_END);

        pack();
        setResizable(false);
        setLocationRelativeTo(frame);

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 100);
        frame.setLayout(new FlowLayout());
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void addComponent(JComponent component, int gridX, int gridY, int gridWidth) {
        gridBagConstraints.gridx = gridX;
        gridBagConstraints.gridy = gridY;
        gridBagConstraints.gridwidth = gridWidth;
        panel.add(component, gridBagConstraints);
    }
}
