package service;

import model.CalculatorModel;
import util.MathUtil;

/**
 * CalculatorService class - Business logic layer
 * Handles all calculator operations and orchestrates between UI and Model
 * Implements separation of concerns principle
 */
public class CalculatorService {
    
    private final CalculatorModel model;
    
    /**
     * Constructor - Initialize with CalculatorModel
     */
    public CalculatorService(CalculatorModel model) {
        this.model = model;
    }
    
    /**
     * Process number input from UI
     */
    public String inputNumber(String digit) {
        if (!isValidDigit(digit)) {
            throw new IllegalArgumentException("Invalid digit: " + digit);
        }
        model.appendDigit(digit);
        return getDisplayValue();
    }
    
    /**
     * Process decimal point input
     */
    public String inputDecimal() {
        model.appendDecimal();
        return getDisplayValue();
    }
    
    /**
     * Process operation input
     */
    public String inputOperation(String operation) {
        if (!isValidOperation(operation)) {
            throw new IllegalArgumentException("Invalid operation: " + operation);
        }
        model.setOperation(operation);
        return getDisplayValue();
    }
    
    /**
     * Calculate and get result
     */
    public String calculate() {
        model.calculate();
        return getDisplayValue();
    }
    
    /**
     * Square root operation
     */
    public String squareRoot() {
        model.squareRoot();
        return getDisplayValue();
    }
    
    /**
     * Percentage operation
     */
    public String percentage() {
        model.percentage();
        return getDisplayValue();
    }
    
    /**
     * Negate current value
     */
    public String negate() {
        model.negate();
        return getDisplayValue();
    }
    
    /**
     * Memory functions
     */
    public void memoryAdd() {
        model.memoryAdd();
    }
    
    public void memorySubtract() {
        model.memorySubtract();
    }
    
    public String memoryRecall() {
        model.memoryRecall();
        return getDisplayValue();
    }
    
    public void memoryClear() {
        model.memoryClear();
    }
    
    /**
     * Clear calculator
     */
    public String clear() {
        model.reset();
        return getDisplayValue();
    }
    
    /**
     * Backspace - remove last character
     */
    public String backspace() {
        String current = model.getCurrentInput();
        if (current.length() > 1) {
            model.setCurrentInput(current.substring(0, current.length() - 1));
        } else {
            model.setCurrentInput("0");
        }
        return getDisplayValue();
    }
    
    /**
     * Get formatted display value
     */
    public String getDisplayValue() {
        try {
            double value = Double.parseDouble(model.getCurrentInput());
            return MathUtil.formatDisplay(value);
        } catch (NumberFormatException e) {
            return model.getCurrentInput();
        }
    }
    
    /**
     * Get calculation history
     */
    public String getHistory() {
        return model.getHistory();
    }
    
    /**
     * Clear history
     */
    public void clearHistory() {
        model.clearHistory();
    }
    
    /**
     * Get memory value
     */
    public double getMemoryValue() {
        return model.getMemory();
    }
    
    /**
     * Validate digit input
     */
    private boolean isValidDigit(String digit) {
        return digit != null && digit.matches("[0-9]");
    }
    
    /**
     * Validate operation input
     */
    private boolean isValidOperation(String operation) {
        return operation != null && 
               (operation.equals("+") || operation.equals("-") || 
                operation.equals("×") || operation.equals("*") ||
                operation.equals("÷") || operation.equals("/") ||
                operation.equals("%") || operation.equals("^"));
    }
    
    /**
     * Get the underlying model (for testing or advanced operations)
     */
    public CalculatorModel getModel() {
        return model;
    }
}
