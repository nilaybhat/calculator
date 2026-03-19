package ui;

import config.AppConfig;
import javax.swing.*;
import java.awt.*;

/**
 * ThemeManager class - Manages application theming and styling
 * Provides centralized management of colors, fonts, and UI styling
 */
public class ThemeManager {
    
    private static final Color PRIMARY_COLOR = hexToColor(AppConfig.COLOR_PRIMARY);
    private static final Color SECONDARY_COLOR = hexToColor(AppConfig.COLOR_SECONDARY);
    private static final Color ACCENT_COLOR = hexToColor(AppConfig.COLOR_ACCENT);
    private static final Color TEXT_COLOR = hexToColor(AppConfig.COLOR_TEXT);
    private static final Color TEXT_DIM_COLOR = hexToColor(AppConfig.COLOR_TEXT_DIM);
    
    /**
     * Apply dark mode theme to components
     */
    public static void applyDarkTheme() {
        UIManager.put("Button.background", PRIMARY_COLOR);
        UIManager.put("Button.foreground", TEXT_COLOR);
        UIManager.put("Panel.background", PRIMARY_COLOR);
        UIManager.put("Label.foreground", TEXT_COLOR);
        UIManager.put("TextField.background", SECONDARY_COLOR);
        UIManager.put("TextField.foreground", TEXT_COLOR);
    }
    
    /**
     * Style a button with modern appearance
     */
    public static void styleButton(JButton button, boolean isAccent) {
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(true);
        
        if (isAccent) {
            button.setBackground(ACCENT_COLOR);
            button.setForeground(Color.BLACK);
        } else {
            button.setBackground(SECONDARY_COLOR);
            button.setForeground(TEXT_COLOR);
        }
        
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(isAccent ? 
                    brighten(ACCENT_COLOR, 0.2) : 
                    brighten(SECONDARY_COLOR, 0.2));
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(isAccent ? ACCENT_COLOR : SECONDARY_COLOR);
            }
        });
    }
    
    /**
     * Style display text field
     */
    public static void styleDisplay(JTextField display) {
        display.setBackground(SECONDARY_COLOR);
        display.setForeground(TEXT_COLOR);
        display.setFont(new Font("Courier New", Font.PLAIN, AppConfig.DISPLAY_FONT_SIZE));
        display.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setCaretColor(TEXT_COLOR);
    }
    
    /**
     * Style text area for history
     */
    public static void styleTextArea(JTextArea textArea) {
        textArea.setBackground(SECONDARY_COLOR);
        textArea.setForeground(TEXT_DIM_COLOR);
        textArea.setFont(new Font("Courier New", Font.PLAIN, AppConfig.HISTORY_FONT_SIZE));
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
    }
    
    /**
     * Convert hex color string to Color object
     */
    private static Color hexToColor(String hex) {
        hex = hex.replace("#", "");
        return new Color(
            Integer.valueOf(hex.substring(0, 2), 16),
            Integer.valueOf(hex.substring(2, 4), 16),
            Integer.valueOf(hex.substring(4, 6), 16)
        );
    }
    
    /**
     * Brighten a color by a factor
     */
    private static Color brighten(Color color, double factor) {
        int red = Math.min(255, (int) (color.getRed() * (1 + factor)));
        int green = Math.min(255, (int) (color.getGreen() * (1 + factor)));
        int blue = Math.min(255, (int) (color.getBlue() * (1 + factor)));
        return new Color(red, green, blue);
    }
    
    /**
     * Get primary color
     */
    public static Color getPrimaryColor() {
        return PRIMARY_COLOR;
    }
    
    /**
     * Get accent color
     */
    public static Color getAccentColor() {
        return ACCENT_COLOR;
    }
    
    // Prevent instantiation
    private ThemeManager() {
    }
}
