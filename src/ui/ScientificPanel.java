// src/ui/ScientificPanel.java
package ui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import logic.CalculatorEngine;

public class ScientificPanel extends JPanel implements ActionListener {
    private CalculatorEngine engine;
    private DisplayPanel displayPanel;
    
    private String[] sciButtons = { 
        "sin", "cos", 
        "tan", "asin", 
        "acos", "atan", 
        "log", "ln", 
        "√", "x^y", 
        "e^x", "n!" 
    };
    
    public ScientificPanel(CalculatorEngine engine, DisplayPanel displayPanel) {
        this.engine = engine;
        this.displayPanel = displayPanel;
        setLayout(new GridLayout(6, 2, 5, 5));
        setBorder(new TitledBorder("Scientific Functions"));
        for(String label : sciButtons) {
            JButton btn = new JButton(label);
            btn.setFont(new Font("Arial", Font.BOLD, 16));
            btn.addActionListener(this);
            add(btn);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        engine.handleScientificFunction(cmd, displayPanel);
    }
}