package util;

import config.AppConfig;

/**
 * Utility class for mathematical operations
 * Provides static methods for various calculations with error handling
 */
public class MathUtil {
    
    /**
     * Performs addition
     */
    public static double add(double a, double b) {
        return a + b;
    }
    
    /**
     * Performs subtraction
     */
    public static double subtract(double a, double b) {
        return a - b;
    }
    
    /**
     * Performs multiplication
     */
    public static double multiply(double a, double b) {
        return a * b;
    }
    
    /**
     * Performs division with zero check
     * @throws ArithmeticException if divisor is zero
     */
    public static double divide(double a, double b) {
        if (isZero(b)) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return a / b;
    }
    
    /**
     * Calculates modulus with zero check
     * @throws ArithmeticException if divisor is zero
     */
    public static double modulus(double a, double b) {
        if (isZero(b)) {
            throw new ArithmeticException("Cannot calculate modulus by zero");
        }
        return a % b;
    }
    
    /**
     * Calculates power (a^b)
     */
    public static double power(double a, double b) {
        return Math.pow(a, b);
    }
    
    /**
     * Calculates square root
     * @throws ArithmeticException if number is negative
     */
    public static double squareRoot(double a) {
        if (a < 0) {
            throw new ArithmeticException("Cannot calculate square root of negative number");
        }
        return Math.sqrt(a);
    }
    
    /**
     * Calculates percentage of a number
     */
    public static double percentage(double a, double b) {
        return (a * b) / 100.0;
    }
    
    /**
     * Checks if a number is effectively zero
     */
    public static boolean isZero(double value) {
        return Math.abs(value) < AppConfig.EPSILON;
    }
    
    /**
     * Rounds a number to specified decimal places
     */
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException("Places must be non-negative");
        long factor = (long) Math.pow(10, places);
        return (double) Math.round(value * factor) / factor;
    }
    
    /**
     * Formats a number for display
     * Removes unnecessary trailing zeros
     */
    public static String formatDisplay(double value) {
        // Handle very small numbers (close to zero)
        if (Math.abs(value) < AppConfig.EPSILON) {
            return "0";
        }
        
        // Round to prevent floating point artifacts
        double rounded = round(value, AppConfig.DECIMAL_PLACES);
        
        // Format as string and remove trailing zeros
        String formatted = String.format("%.8f", rounded);
        formatted = formatted.replaceAll("0*$", "").replaceAll("\\.$", "");
        
        return formatted.isEmpty() ? "0" : formatted;
    }
    
    // Prevent instantiation
    private MathUtil() {
    }
}
