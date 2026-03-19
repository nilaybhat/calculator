package main;

import ui.CalculatorUI;
import javax.swing.*;

/**
 * Main class - Application entry point
 * Initializes and launches the calculator application
 */
public class Main {
    
    /**
     * Main method - Entry point of the application
     */
    public static void main(String[] args) {
        // Set look and feel to system default
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Failed to set look and feel: " + e.getMessage());
        }
        
        // Launch UI on Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            CalculatorUI calculator = new CalculatorUI();
            calculator.setVisible(true);
        });
    }
}
