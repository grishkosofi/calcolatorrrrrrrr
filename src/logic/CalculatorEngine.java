// src/logic/CalculatorEngine.java
package logic;

import ui.DisplayPanel;
import ui.HistoryPanel;

public class CalculatorEngine {
    private double operand1 = 0;
    private String operator = "";
    private boolean startNewNumber = true;
    private double memoryValue = 0;
    private boolean useDegrees = true;
    private HistoryPanel historyPanel;

    public void setHistoryPanel(HistoryPanel historyPanel) {
        this.historyPanel = historyPanel;
    }
    
    public void setUseDegrees(boolean useDegrees) {
        this.useDegrees = useDegrees;
    }
    
    public boolean isUseDegrees() {
        return useDegrees;
    }
    
    // Обработчик ввода для цифровых и базовых операций
    public void handleNumericInput(String input, DisplayPanel displayPanel) {
        String currentText = displayPanel.getText();
        if (startNewNumber && !input.equals("=")) {
            currentText = "";
            startNewNumber = false;
        }
        
        switch(input) {
            case "C":
                displayPanel.setText("");
                operator = "";
                operand1 = 0;
                startNewNumber = true;
                break;
            case "=":
                try {
                    double operand2 = Double.parseDouble(currentText);
                    double result = computeBinaryOperation(operand1, operand2, operator);
                    displayPanel.setText(Double.toString(result));
                    if (historyPanel != null) {
                        historyPanel.appendHistory(operand1 + " " + operator + " " + operand2 + " = " + result);
                    }
                    operand1 = result;
                    operator = "";
                    startNewNumber = true;
                } catch(NumberFormatException ex) {
                    displayPanel.setText("Invalid Input");
                } catch(ArithmeticException ex) {
                    displayPanel.setText("Math Error");
                }
                break;
            case "+": case "-": case "*": case "/": case "%": case "x^y":
                try {
                    operand1 = Double.parseDouble(currentText);
                    operator = input;
                    startNewNumber = true;
                } catch(NumberFormatException ex) {
                    displayPanel.setText("Invalid Input");
                }
                break;
            case "+/-":
            case "±":
                try {
                    double value = Double.parseDouble(currentText);
                    displayPanel.setText(Double.toString(-value));
                } catch(NumberFormatException ex) {
                    displayPanel.setText("Invalid Input");
                }
                break;
            default:
                displayPanel.setText(currentText + input);
                break;
        }
    }
    
    // Обработка унарных (научных) функций
    public void handleScientificFunction(String function, DisplayPanel displayPanel) {
        try {
            double value = Double.parseDouble(displayPanel.getText());
            double result = 0;
            switch(function) {
                case "sin":
                    result = Math.sin(useDegrees ? Math.toRadians(value) : value);
                    break;
                case "cos":
                    result = Math.cos(useDegrees ? Math.toRadians(value) : value);
                    break;
                case "tan":
                    result = Math.tan(useDegrees ? Math.toRadians(value) : value);
                    break;
                case "asin":
                    result = useDegrees ? Math.toDegrees(Math.asin(value)) : Math.asin(value);
                    break;
                case "acos":
                    result = useDegrees ? Math.toDegrees(Math.acos(value)) : Math.acos(value);
                    break;
                case "atan":
                    result = useDegrees ? Math.toDegrees(Math.atan(value)) : Math.atan(value);
                    break;
                case "log":
                    if(value <= 0) throw new ArithmeticException("Invalid log");
                    result = Math.log10(value);
                    break;
                case "ln":
                    if(value <= 0) throw new ArithmeticException("Invalid ln");
                    result = Math.log(value);
                    break;
                case "√":
                    if(value < 0) throw new ArithmeticException("Invalid sqrt");
                    result = Math.sqrt(value);
                    break;
                case "e^x":
                    result = Math.exp(value);
                    break;
                case "n!":
                    if(value < 0 || value != (int)value)
                        throw new ArithmeticException("Invalid factorial");
                    result = factorial((int)value);
                    break;
                case "x^y":
                    operand1 = value;
                    operator = "x^y";
                    startNewNumber = true;
                    return;
                default:
                    break;
            }
            displayPanel.setText(Double.toString(result));
            if(historyPanel != null) {
                historyPanel.appendHistory(function + "(" + value + ") = " + result);
            }
            startNewNumber = true;
        } catch(NumberFormatException ex) {
            displayPanel.setText("Invalid Input");
        } catch(ArithmeticException ex) {
            displayPanel.setText("Math Error");
        }
    }
    
    // Обработка операций памяти
    public void handleMemoryOperation(String op, DisplayPanel displayPanel) {
        try {
            switch(op) {
                case "MS":
                    memoryValue = Double.parseDouble(displayPanel.getText());
                    break;
                case "MR":
                    displayPanel.setText(Double.toString(memoryValue));
                    startNewNumber = true;
                    break;
                case "MC":
                    memoryValue = 0;
                    break;
                case "M+":
                    memoryValue += Double.parseDouble(displayPanel.getText());
                    break;
                case "M-":
                    memoryValue -= Double.parseDouble(displayPanel.getText());
                    break;
                default:
                    break;
            }
        } catch(NumberFormatException ex) {
            displayPanel.setText("Invalid Input");
        }
    }
    
    // Метод вычисления бинарных операций
    public double computeBinaryOperation(double op1, double op2, String op) throws ArithmeticException {
        switch(op) {
            case "+":
                return op1 + op2;
            case "-":
                return op1 - op2;
            case "*":
                return op1 * op2;
            case "/":
                if(op2 == 0) throw new ArithmeticException("Division by zero");
                return op1 / op2;
            case "%":
                if(op2 == 0) throw new ArithmeticException("Division by zero");
                return op1 % op2;
            case "x^y":
                return Math.pow(op1, op2);
            default:
                return op2;
        }
    }
    
    // Метод вычисления факториала
    public long factorial(int n) {
        long fact = 1;
        for (int i = 2; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
}