// src/ui/DisplayPanel.java
package ui;

import javax.swing.*;
import java.awt.*;

public class DisplayPanel extends JPanel {
    private JTextField displayField;

    public DisplayPanel() {
        setLayout(new BorderLayout());
        displayField = new JTextField();
        displayField.setFont(new Font("Arial", Font.BOLD, 24));
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        displayField.setEditable(false);
        add(displayField, BorderLayout.CENTER);
    }

    public void setText(String text) {
        displayField.setText(text);
    }

    public String getText() {
        return displayField.getText();
    }
}