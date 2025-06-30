// src/app/ScientificCalculatorApp.java
package app;

import javax.swing.SwingUtilities;
import ui.CalculatorFrame;

public class ScientificCalculatorApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculatorFrame().setVisible(true));
    }
}