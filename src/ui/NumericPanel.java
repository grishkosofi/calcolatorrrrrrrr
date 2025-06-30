// src/ui/NumericPanel.java
package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import logic.CalculatorEngine;

public class NumericPanel extends JPanel implements ActionListener {
    private CalculatorEngine engine;
    private DisplayPanel displayPanel;

    private String[] buttonLabels = { 
        "7", "8", "9", "/",
        "4", "5", "6", "*",
        "1", "2", "3", "-",
        "0", ".", "+/-", "+",
        "C", "=", "±", "%" 
    };

    public NumericPanel(CalculatorEngine engine, DisplayPanel displayPanel) {
        this.engine = engine;
        this.displayPanel = displayPanel;
        setLayout(new GridLayout(5, 4, 5, 5));
        for (String label : buttonLabels) {
            JButton btn = new JButton(label);
            btn.setFont(new Font("Arial", Font.BOLD, 18));
            btn.addActionListener(this);
            add(btn);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        engine.handleNumericInput(cmd, displayPanel);
    }
}