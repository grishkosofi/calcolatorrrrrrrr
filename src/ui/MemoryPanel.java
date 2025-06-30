// src/ui/MemoryPanel.java
package ui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import logic.CalculatorEngine;

public class MemoryPanel extends JPanel implements ActionListener {
    private CalculatorEngine engine;
    private DisplayPanel displayPanel;
    private String[] memButtons = { "MS", "MR", "MC", "M+", "M-" };
    
    public MemoryPanel(CalculatorEngine engine, DisplayPanel displayPanel) {
        this.engine = engine;
        this.displayPanel = displayPanel;
        setLayout(new GridLayout(1, 5, 5, 5));
        setBorder(new TitledBorder("Memory"));
        for(String label : memButtons) {
            JButton btn = new JButton(label);
            btn.setFont(new Font("Arial", Font.BOLD, 16));
            btn.addActionListener(this);
            add(btn);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        engine.handleMemoryOperation(cmd, displayPanel);
    }
}