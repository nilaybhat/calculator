package ui;

import config.AppConfig;
import model.CalculatorModel;
import service.CalculatorService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * CalculatorUI class - Main user interface
 * Builds and manages the graphical interface using Java Swing
 */
public class CalculatorUI extends JFrame {
    
    private final CalculatorService calculatorService;
    private JTextField displayField;
    private JTextArea historyArea;
    private JLabel memoryLabel;
    
    /**
     * Constructor - Initialize UI
     */
    public CalculatorUI() {
        this.calculatorService = new CalculatorService(new CalculatorModel());
        initializeUI();
    }
    
    /**
     * Initialize the user interface
     */
    private void initializeUI() {
        // Apply theme
        ThemeManager.applyDarkTheme();
        
        // Frame settings
        setTitle(AppConfig.APP_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
        setSize(AppConfig.WINDOW_WIDTH, AppConfig.WINDOW_HEIGHT);
        setMinimumSize(new Dimension(350, 450));
        
        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(5, 5));
        mainPanel.setBackground(ThemeManager.getPrimaryColor());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Top panel (Display + History)
        JPanel topPanel = createTopPanel();
        mainPanel.add(topPanel, BorderLayout.NORTH);
        
        // Center panel (Buttons)
        JPanel buttonPanel = createButtonPanel();
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        
        // Set content pane
        setContentPane(mainPanel);
        
        // Add keyboard support
        addKeyboardSupport();
    }
    
    /**
     * Create top panel with display and history
     */
    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        topPanel.setBackground(ThemeManager.getPrimaryColor());
        
        // Memory label
        memoryLabel = new JLabel("M: 0");
        memoryLabel.setForeground(new Color(200, 200, 200));
        memoryLabel.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        
        // Display field
        displayField = new JTextField("0");
        ThemeManager.styleDisplay(displayField);
        
        JPanel displayPanel = new JPanel(new BorderLayout(5, 0));
        displayPanel.setBackground(ThemeManager.getPrimaryColor());
        displayPanel.add(memoryLabel, BorderLayout.NORTH);
        displayPanel.add(displayField, BorderLayout.CENTER);
        
        topPanel.add(displayPanel, BorderLayout.NORTH);
        
        // History area
        historyArea = new JTextArea(4, 0);
        ThemeManager.styleTextArea(historyArea);
        JScrollPane scrollPane = new JScrollPane(historyArea);
        scrollPane.setBackground(ThemeManager.getPrimaryColor());
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 1));
        scrollPane.getViewport().setBackground(new Color(45, 45, 45));
        
        topPanel.add(scrollPane, BorderLayout.CENTER);
        
        return topPanel;
    }
    
    /**
     * Create button panel with calculator buttons
     */
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(
            AppConfig.GRID_ROWS,
            AppConfig.GRID_COLS,
            AppConfig.BUTTON_SPACING,
            AppConfig.BUTTON_SPACING
        ));
        buttonPanel.setBackground(ThemeManager.getPrimaryColor());
        
        // Button configuration: label, isAccent
        String[][] buttons = {
            {"MC", "M+", "M-", "MR"},
            {"C", "←", "√", "÷"},
            {"7", "8", "9", "×"},
            {"4", "5", "6", "-"},
            {"1", "2", "3", "+"},
            {"0", ".", "%", "^"},
            {"=", "Clear", "History", "±"}
        };
        
        for (String[] row : buttons) {
            for (String label : row) {
                JButton button = new JButton(label);
                boolean isAccent = label.equals("=");
                ThemeManager.styleButton(button, isAccent);
                
                button.addActionListener(e -> handleButtonClick(label));
                buttonPanel.add(button);
            }
        }
        
        return buttonPanel;
    }
    
    /**
     * Handle button clicks from calculator UI
     */
    private void handleButtonClick(String buttonLabel) {
        try {
            switch (buttonLabel) {
                // Numbers
                case "0": case "1": case "2": case "3": case "4":
                case "5": case "6": case "7": case "8": case "9":
                    displayField.setText(calculatorService.inputNumber(buttonLabel));
                    break;
                    
                // Operations
                case "+": case "-": case "×": case "÷": case "^":
                    displayField.setText(calculatorService.inputOperation(buttonLabel));
                    break;
                    
                // Decimal
                case ".":
                    displayField.setText(calculatorService.inputDecimal());
                    break;
                    
                // Equals
                case "=":
                    displayField.setText(calculatorService.calculate());
                    updateHistory();
                    break;
                    
                // Clear
                case "C":
                    displayField.setText(calculatorService.clear());
                    break;
                    
                // Backspace
                case "←":
                    displayField.setText(calculatorService.backspace());
                    break;
                    
                // Square root
                case "√":
                    displayField.setText(calculatorService.squareRoot());
                    break;
                    
                // Percentage
                case "%":
                    displayField.setText(calculatorService.percentage());
                    break;
                    
                // Negate
                case "±":
                    displayField.setText(calculatorService.negate());
                    break;
                    
                // Memory functions
                case "M+":
                    calculatorService.memoryAdd();
                    updateMemoryLabel();
                    break;
                    
                case "M-":
                    calculatorService.memorySubtract();
                    updateMemoryLabel();
                    break;
                    
                case "MC":
                    calculatorService.memoryClear();
                    updateMemoryLabel();
                    break;
                    
                case "MR":
                    displayField.setText(calculatorService.memoryRecall());
                    break;
                    
                // History
                case "History":
                    toggleHistory();
                    break;
                    
                case "Clear":
                    calculatorService.clearHistory();
                    historyArea.setText("");
                    break;
            }
        } catch (Exception e) {
            displayField.setText("Error");
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Update history display
     */
    private void updateHistory() {
        historyArea.setText(calculatorService.getHistory());
        // Auto-scroll to bottom
        historyArea.setCaretPosition(historyArea.getDocument().getLength());
    }
    
    /**
     * Update memory label
     */
    private void updateMemoryLabel() {
        double memoryValue = calculatorService.getMemoryValue();
        String memoryText = Math.abs(memoryValue) < 1e-10 ? "M: 0" : 
                           String.format("M: %.2f", memoryValue);
        memoryLabel.setText(memoryText);
    }
    
    /**
     * Toggle history visibility
     */
    private void toggleHistory() {
        updateHistory();
        JOptionPane.showMessageDialog(
            this,
            historyArea.getText(),
            "Calculation History",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    /**
     * Add keyboard support for calculator
     */
    private void addKeyboardSupport() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char keyChar = e.getKeyChar();
                int keyCode = e.getKeyCode();
                
                if (keyChar >= '0' && keyChar <= '9') {
                    displayField.setText(calculatorService.inputNumber(String.valueOf(keyChar)));
                } else if (keyChar == '+' || keyChar == '-' || keyChar == '*' || keyChar == '/') {
                    String op = keyChar == '*' ? "×" : keyChar == '/' ? "÷" : String.valueOf(keyChar);
                    displayField.setText(calculatorService.inputOperation(op));
                } else if (keyChar == '.') {
                    displayField.setText(calculatorService.inputDecimal());
                } else if (keyChar == '%') {
                    displayField.setText(calculatorService.percentage());
                } else if (keyCode == KeyEvent.VK_ENTER || keyCode == KeyEvent.VK_EQUALS) {
                    displayField.setText(calculatorService.calculate());
                    updateHistory();
                } else if (keyCode == KeyEvent.VK_BACK_SPACE) {
                    displayField.setText(calculatorService.backspace());
                } else if (keyCode == KeyEvent.VK_DELETE) {
                    displayField.setText(calculatorService.clear());
                }
            }
        });
        
        setFocusable(true);
    }
    
    /**
     * Main entry point for UI testing
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatorUI calculator = new CalculatorUI();
            calculator.setVisible(true);
        });
    }
}
