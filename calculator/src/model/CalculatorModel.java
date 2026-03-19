package model;

import util.MathUtil;

/**
 * CalculatorModel class represents the state and data of the calculator
 * Encapsulates all calculator state and provides methods to manage it
 */
public class CalculatorModel {
    
    private double currentValue;
    private double previousValue;
    private String currentInput;
    private String operation;
    private double memory;
    private StringBuilder history;
    private boolean shouldResetDisplay;
    
    /**
     * Constructor - Initialize calculator state
     */
    public CalculatorModel() {
        reset();
    }
    
    /**
     * Reset calculator to initial state
     */
    public void reset() {
        this.currentValue = 0;
        this.previousValue = 0;
        this.currentInput = "0";
        this.operation = null;
        this.memory = 0;
        this.history = new StringBuilder();
        this.shouldResetDisplay = false;
    }
    
    /**
     * Append digit to current input
     */
    public void appendDigit(String digit) {
        if (shouldResetDisplay) {
            currentInput = digit;
            shouldResetDisplay = false;
        } else {
            if ("0".equals(currentInput)) {
                currentInput = digit;
            } else {
                currentInput += digit;
            }
        }
    }
    
    /**
     * Append decimal point to current input
     */
    public void appendDecimal() {
        if (shouldResetDisplay) {
            currentInput = "0.";
            shouldResetDisplay = false;
        } else if (!currentInput.contains(".")) {
            currentInput += ".";
        }
    }
    
    /**
     * Set operation and prepare for next input
     */
    public void setOperation(String op) {
        if (!currentInput.isEmpty()) {
            try {
                currentValue = Double.parseDouble(currentInput);
            } catch (NumberFormatException e) {
                currentValue = 0;
            }
        }
        
        this.operation = op;
        this.currentInput = "0";
        this.shouldResetDisplay = true;
    }
    
    /**
     * Calculate result of current operation
     */
    public void calculate() {
        if (operation == null) {
            return;
        }
        
        try {
            double secondValue = Double.parseDouble(currentInput);
            double result = performOperation(currentValue, secondValue, operation);
            
            // Add to history
            history.append(currentValue).append(" ").append(operation).append(" ")
                   .append(secondValue).append(" = ").append(result).append("\n");
            
            currentValue = result;
            currentInput = String.valueOf(result);
            operation = null;
            shouldResetDisplay = true;
            
        } catch (NumberFormatException | ArithmeticException e) {
            currentInput = "Error";
            shouldResetDisplay = true;
        }
    }
    
    /**
     * Perform the specified mathematical operation
     */
    private double performOperation(double a, double b, String op) {
        switch (op) {
            case "+":
                return MathUtil.add(a, b);
            case "-":
                return MathUtil.subtract(a, b);
            case "×":
            case "*":
                return MathUtil.multiply(a, b);
            case "÷":
            case "/":
                return MathUtil.divide(a, b);
            case "%":
                return MathUtil.modulus(a, b);
            case "^":
                return MathUtil.power(a, b);
            default:
                throw new IllegalArgumentException("Unknown operation: " + op);
        }
    }
    
    /**
     * Square root operation
     */
    public void squareRoot() {
        try {
            double value = Double.parseDouble(currentInput);
            double result = MathUtil.squareRoot(value);
            
            history.append("√").append(value).append(" = ").append(result).append("\n");
            
            currentInput = String.valueOf(result);
            shouldResetDisplay = true;
        } catch (NumberFormatException | ArithmeticException e) {
            currentInput = "Error";
        }
    }
    
    /**
     * Percentage operation
     */
    public void percentage() {
        try {
            double value = Double.parseDouble(currentInput);
            double result = value / 100.0;
            currentInput = String.valueOf(result);
        } catch (NumberFormatException e) {
            currentInput = "Error";
        }
    }
    
    /**
     * Negate current value
     */
    public void negate() {
        try {
            double value = Double.parseDouble(currentInput);
            currentInput = String.valueOf(-value);
        } catch (NumberFormatException e) {
            currentInput = "Error";
        }
    }
    
    /**
     * Memory functions
     */
    public void memoryAdd() {
        try {
            memory += Double.parseDouble(currentInput);
        } catch (NumberFormatException e) {
            // Ignore
        }
    }
    
    public void memorySubtract() {
        try {
            memory -= Double.parseDouble(currentInput);
        } catch (NumberFormatException e) {
            // Ignore
        }
    }
    
    public void memoryRecall() {
        currentInput = String.valueOf(memory);
        shouldResetDisplay = true;
    }
    
    public void memoryClear() {
        memory = 0;
    }
    
    // Getters and Setters
    public double getCurrentValue() {
        return currentValue;
    }
    
    public String getCurrentInput() {
        return currentInput;
    }
    
    public void setCurrentInput(String input) {
        this.currentInput = input;
    }
    
    public String getOperation() {
        return operation;
    }
    
    public double getMemory() {
        return memory;
    }
    
    public String getHistory() {
        return history.toString();
    }
    
    public void clearHistory() {
        history = new StringBuilder();
    }
    
    public boolean shouldResetDisplay() {
        return shouldResetDisplay;
    }
    
    public void setShouldResetDisplay(boolean value) {
        this.shouldResetDisplay = value;
    }
}
