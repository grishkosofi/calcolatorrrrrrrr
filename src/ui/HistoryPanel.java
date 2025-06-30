// src/ui/HistoryPanel.java
package ui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class HistoryPanel extends JPanel {
    private JTextArea historyArea;
    
    public HistoryPanel() {
        setLayout(new BorderLayout());
        historyArea = new JTextArea(5, 40);
        historyArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(historyArea);
        scrollPane.setBorder(new TitledBorder("History"));
        add(scrollPane, BorderLayout.CENTER);
    }
    
    public void appendHistory(String text) {
        historyArea.append(text + "\n");
    }
}