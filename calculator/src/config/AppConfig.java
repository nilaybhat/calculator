package config;

/**
 * Application configuration class
 * Central place for all application constants and configuration settings
 */
public class AppConfig {
    
    // Window settings
    public static final String APP_TITLE = "Professional Calculator";
    public static final int WINDOW_WIDTH = 400;
    public static final int WINDOW_HEIGHT = 550;
    
    // UI Settings
    public static final int BUTTON_SIZE = 60;
    public static final int GRID_ROWS = 7;
    public static final int GRID_COLS = 4;
    public static final int BUTTON_SPACING = 5;
    
    // Display settings
    public static final int DISPLAY_FONT_SIZE = 24;
    public static final int HISTORY_FONT_SIZE = 12;
    
    // Memory settings
    public static final double MEMORY_DEFAULT = 0.0;
    
    // Math settings
    public static final double EPSILON = 1e-10;  // For floating point comparisons
    public static final int DECIMAL_PLACES = 8;
    
    // Color scheme (Dark mode)
    public static final String COLOR_PRIMARY = "#2d2d2d";
    public static final String COLOR_SECONDARY = "#3d3d3d";
    public static final String COLOR_ACCENT = "#ff9800";
    public static final String COLOR_TEXT = "#ffffff";
    public static final String COLOR_TEXT_DIM = "#999999";
    
    // Prevent instantiation
    private AppConfig() {
    }
}
