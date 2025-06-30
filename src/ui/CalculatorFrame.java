// src/ui/CalculatorFrame.java
package ui;

import javax.swing.*;
import java.awt.*;
import logic.CalculatorEngine;

public class CalculatorFrame extends JFrame {

    private DisplayPanel displayPanel;
    private NumericPanel numericPanel;
    private ScientificPanel scientificPanel;
    private MemoryPanel memoryPanel;
    private HistoryPanel historyPanel;
    private CalculatorEngine engine;

    public CalculatorFrame() {
        setTitle("Scientific Calculator");
        setSize(500, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));

        // Инициализация логики расчёта
        engine = new CalculatorEngine();

        // Панель отображения
        displayPanel = new DisplayPanel();
        add(displayPanel, BorderLayout.NORTH);

        // Центральная панель: цифровая клавиатура и основные операции
        numericPanel = new NumericPanel(engine, displayPanel);
        add(numericPanel, BorderLayout.CENTER);

        // Панель научных функций (расположена справа)
        scientificPanel = new ScientificPanel(engine, displayPanel);
        add(scientificPanel, BorderLayout.EAST);

        // Нижняя часть окна: панель памяти, переключатель режимов углов и история
        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        memoryPanel = new MemoryPanel(engine, displayPanel);
        bottomPanel.add(memoryPanel, BorderLayout.NORTH);

        // Переключатель для выбора режима углов (Degrees / Radians)
        JPanel togglePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JToggleButton toggleButton = new JToggleButton("Degrees", true);
        toggleButton.addActionListener(e -> {
            engine.setUseDegrees(toggleButton.isSelected());
            toggleButton.setText(engine.isUseDegrees() ? "Degrees" : "Radians");
        });
        togglePanel.add(toggleButton);
        bottomPanel.add(togglePanel, BorderLayout.CENTER);

        // Область истории вычислений
        historyPanel = new HistoryPanel();
        engine.setHistoryPanel(historyPanel);
        bottomPanel.add(historyPanel, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);
    }
}