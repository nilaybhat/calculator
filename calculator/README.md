# Professional Calculator Application in Java

A modern, production-quality calculator application built with Java Swing. Features a clean architecture with separation of concerns, comprehensive mathematical operations, memory functions, and a dark-themed GUI.

## Features

### Core Operations
- ✅ **Basic Arithmetic**: Addition, Subtraction, Multiplication, Division
- ✅ **Advanced Operations**: Power, Modulus, Square Root, Percentage
- ✅ **Memory Functions**: M+ (Add to Memory), M- (Subtract from Memory), MR (Recall), MC (Clear)
- ✅ **Utility Functions**: Negate (±), Backspace (←), Clear
- ✅ **Additional Features**: Calculation history, decimal input, keyboard support

### User Interface
- **Dark Mode Theme**: Modern, eye-friendly dark interface with accent colors
- **Responsive Design**: Adapts to window resizing
- **Grid Layout**: Professional button arrangement (7 rows × 4 columns)
- **Real-time Display**: Shows current calculations and results
- **History Panel**: View calculation history in real-time
- **Hover Effects**: Interactive button feedback
- **Keyboard Support**: Full keyboard input support

## Project Architecture

### Folder Structure
```
CalculatorApp/
├── src/
│   ├── main/
│   │   └── Main.java                 # Application entry point
│   ├── model/
│   │   └── CalculatorModel.java      # Calculator state and data
│   ├── service/
│   │   └── CalculatorService.java    # Business logic layer
│   ├── util/
│   │   └── MathUtil.java             # Mathematical utility functions
│   ├── ui/
│   │   ├── CalculatorUI.java         # Main GUI implementation
│   │   └── ThemeManager.java         # UI theming and styling
│   └── config/
│       └── AppConfig.java            # Application configuration
└── README.md                         # This file
```

## Class Documentation

### 1. **AppConfig.java** - Configuration Layer
**Purpose**: Centralized configuration management  
**Key Responsibilities**:
- Defines all application constants (window size, fonts, colors)
- Provides theme color configuration for dark mode
- Centralizes magic numbers to prevent hardcoding
- Easy to modify without touching business logic

**Key Constants**:
- `APP_TITLE`: Application window title
- `WINDOW_WIDTH/HEIGHT`: Default window dimensions
- `COLOR_*`: Theme colors in hex format
- `DECIMAL_PLACES`: Floating-point precision

---

### 2. **MathUtil.java** - Utility Layer
**Purpose**: Pure mathematical operations with error handling  
**Key Methods**:
```java
add(double a, double b)              // Basic addition
subtract(double a, double b)         // Basic subtraction
multiply(double a, double b)         // Basic multiplication
divide(double a, double b)           // Division with zero-check
modulus(double a, double b)          // Modulus with error handling
power(double a, double b)            // Exponentiation
squareRoot(double a)                 // Square root with validation
percentage(double a, double b)       // Percentage calculation
isZero(double value)                 // Floating-point zero comparison
round(double value, int places)      // Precise rounding
formatDisplay(double value)          // Format for UI display
```

**Features**:
- All operations include error handling
- Division by zero protection
- Negative number validation for square root
- Floating-point precision management
- Display formatting (removes trailing zeros)

---

### 3. **CalculatorModel.java** - Data Model Layer
**Purpose**: Encapsulates calculator state  
**Key Responsibilities**:
- Manages current value, operation, and input state
- Handles memory storage
- Maintains calculation history
- Implements state reset and operations

**Key State Variables**:
- `currentValue`: Last operand
- `previousValue`: First operand
- `currentInput`: User input display
- `operation`: Currently selected operation
- `memory`: Memory storage value
- `history`: Calculation history log
- `shouldResetDisplay`: Flag for display reset

**Key Methods**:
- `appendDigit(String digit)`: Add digit to input
- `appendDecimal()`: Add decimal point
- `setOperation(String op)`: Set mathematical operation
- `calculate()`: Perform operation and store history
- `reset()`: Clear all state to defaults
- Memory functions: `memoryAdd()`, `memorySubtract()`, etc.

---

### 4. **CalculatorService.java** - Service/Business Logic Layer
**Purpose**: Orchestrates calculator operations between UI and Model  
**Design Pattern**: Service layer pattern for separation of concerns

**Key Responsibilities**:
- Validates user input
- Delegates operations to model and utilities
- Formats output for display
- Handles error conditions gracefully

**Key Methods**:
```java
inputNumber(String digit)            // Process number input
inputOperation(String operation)     // Process operation input
calculate()                          // Calculate and update display
squareRoot()                         // Perform square root
percentage()                         // Calculate percentage
backspace()                          // Remove last digit
clear()                             // Reset calculator
getDisplayValue()                   // Get formatted display text
getHistory()                        // Retrieve calculation history
```

**Input Validation**:
- Validates all digits (0-9)
- Validates operation symbols (+, -, ×, ÷, %, ^)
- Prevents invalid operations
- Throws meaningful exceptions

---

### 5. **ThemeManager.java** - UI Theming Layer
**Purpose**: Centralized UI styling and theming  
**Benefits**:
- Single source of truth for colors and styling
- Easy theme switching capability
- Consistent look and feel across UI
- Hover effects and interactive feedback

**Key Methods**:
```java
applyDarkTheme()                 // Sets global dark theme
styleButton(JButton, boolean)    // Styles calculator buttons
styleDisplay(JTextField)         // Styles display field
styleTextArea(JTextArea)        // Styles history area
hexToColor(String hex)          // Converts hex to Color
brighten(Color, double)         // Brightens color for hover
```

**Color Scheme**:
- **Primary**: #2d2d2d (Dark background)
- **Secondary**: #3d3d3d (Button background)
- **Accent**: #ff9800 (Orange - equals button)
- **Text**: #ffffff (White)
- **Text Dim**: #999999 (Gray - history text)

---

### 6. **CalculatorUI.java** - User Interface Layer
**Purpose**: Main GUI implementation using Java Swing  
**Architecture**: MVC-inspired (View layer + Controller logic)

**Key Components**:
- **JFrame**: Main application window
- **JTextField**: Display field for calculations
- **JTextArea**: History panel
- **JButton Grid**: 7×4 calculator buttons
- **Memory Label**: Shows current memory value

**Key Methods**:
```java
initializeUI()              // Initialize all UI components
createTopPanel()           // Create display + history section
createButtonPanel()        // Create calculator button grid
handleButtonClick()        // Process button events
addKeyboardSupport()       // Enable keyboard input
updateHistory()           // Update history display
updateMemoryLabel()       // Update memory label
```

**Button Layout**:
```
[MC] [M+] [M-] [MR]
[C]  [←]  [√]  [÷]
[7]  [8]  [9]  [×]
[4]  [5]  [6]  [-]
[1]  [2]  [3]  [+]
[0]  [.]  [%]  [^]
[=]  [Clear] [History] [±]
```

**Keyboard Support**:
- **Numbers**: 0-9 keys
- **Operations**: +, -, *, /
- **Decimal**: . key
- **Calculate**: Enter or = key
- **Backspace**: Backspace key
- **Clear**: Delete key

---

### 7. **Main.java** - Application Entry Point
**Purpose**: Launches the calculator application  
**Responsibilities**:
- Sets system look and feel
- Initializes CalculatorUI
- Runs UI on Event Dispatch Thread (EDT)

## Clean Code Principles Applied

### 1. **Encapsulation**
- Private variables with getter/setter methods
- State management isolated in Model
- Logic separated from presentation

### 2. **Separation of Concerns**
- **Model**: Data and state management
- **Service**: Business logic
- **UI**: Presentation layer
- **Config**: Configuration
- **Util**: Pure utility functions

### 3. **Single Responsibility**
- Each class has one clear purpose
- CalculatorModel manages state
- CalculatorService orchestrates operations
- CalculatorUI handles presentation

### 4. **Error Handling**
- Division by zero protection
- Negative square root validation
- Try-catch blocks for robust operation
- User-friendly error messages

### 5. **Code Reusability**
- MathUtil functions used across application
- ThemeManager centralizes styling
- CalculatorService abstracts business logic

### 6. **Maintainability**
- Clear naming conventions
- Comprehensive Javadoc comments
- Constants instead of magic numbers
- Modular design for easy extension

### 7. **Validation & Constraints**
- Input validation in CalculatorService
- Floating-point comparison using EPSILON
- Method contracts documented

## Compilation & Execution

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Command-line terminal or IDE (IntelliJ IDEA, Eclipse, VS Code)

### Step 1: Navigate to Project Directory
```bash
cd d:\calculator
```

### Step 2: Compile All Java Files
```bash
javac -d . src/config/AppConfig.java src/util/MathUtil.java src/model/CalculatorModel.java src/service/CalculatorService.java src/ui/ThemeManager.java src/ui/CalculatorUI.java src/main/Main.java
```

**Alternative - Compile with Wildcard**:
```bash
javac -d . src/**/*.java
```

### Step 3: Run the Application
```bash
java main.Main
```

### Complete Commands (Copy & Paste)
```bash
# Compile
javac -d . src/config/AppConfig.java src/util/MathUtil.java src/model/CalculatorModel.java src/service/CalculatorService.java src/ui/ThemeManager.java src/ui/CalculatorUI.java src/main/Main.java

# Run
java main.Main
```

## Build Script (Optional)

Create `build.bat` for easy compilation on Windows:

```bat
@echo off
echo Compiling Java Calculator Application...
javac -d . src/config/AppConfig.java^
            src/util/MathUtil.java^
            src/model/CalculatorModel.java^
            src/service/CalculatorService.java^
            src/ui/ThemeManager.java^
            src/ui/CalculatorUI.java^
            src/main/Main.java

if %errorlevel% equ 0 (
    echo Compilation successful!
    echo.
    echo Running application...
    java main.Main
) else (
    echo Compilation failed!
    pause
)
```

Run with: `build.bat`

## Usage Guide

### Basic Calculations
1. **Addition**: `5 + 3 = ` → Result: `8`
2. **Subtraction**: `10 - 4 = ` → Result: `6`
3. **Multiplication**: `7 × 6 = ` → Result: `42`
4. **Division**: `20 ÷ 4 = ` → Result: `5`

### Advanced Operations
- **Power**: `2 ^ 8 = ` → Result: `256`
- **Square Root**: `√ 16 = ` → Result: `4`
- **Modulus**: `7 % 3 = ` → Result: `1`
- **Percentage**: `50 % = ` → Result: `0.5`
- **Negate**: `±` → Toggles sign of current number

### Memory Functions
- **M+**: Add current number to memory
- **M-**: Subtract current number from memory
- **MR**: Recall memory value to display
- **MC**: Clear memory (reset to 0)

### Other Functions
- **C**: Clear calculator and reset display
- **←**: Delete last digit (backspace)
- **Clear**: Remove all calculation history
- **History**: View all previous calculations
- **.: Decimal point input**

## Implementation Details

### Floating Point Handling
- Uses epsilon comparison (1e-10) for zero checks
- Handles precision with configurable decimal places
- Formats display output to remove trailing zeros
- Prevents floating-point arithmetic artifacts

### Event Handling
- Action listeners for all buttons
- Key listeners for full keyboard support
- Mouse listeners for hover effects
- Thread-safe UI updates via Event Dispatch Thread

### State Management
- Stateless service layer (no global state)
- Model encapsulates all state
- Easy to serialize/persist if needed
- Clear separation between UI state and business state

## Extension Points

The architecture supports easy extension:

### Add New Operation
1. Add method to MathUtil
2. Add case in CalculatorService
3. Add button in CalculatorUI

### Add New Theme
1. Add color constants to AppConfig
2. Create new theme method in ThemeManager
3. Switch themes in CalculatorUI

### Add History Persistence
1. Serialize model to JSON
2. Load on startup
3. No changes to existing code

## Project Statistics

- **Total Lines of Code**: ~850
- **Number of Classes**: 7
- **Number of Methods**: 50+
- **Number of Operations**: 10+
- **GUI Controls**: 30+ interactive buttons and text fields

## Future Enhancement Ideas

1. **Expression Evaluation**: Parse and evaluate complex expressions
2. **Unit Conversion**: Convert between units (cm to inches, etc.)
3. **Scientific Notation**: Support for scientific notation input/output
4. **Trigonometric Functions**: Sin, Cos, Tan operations
5. **Calculation Persistence**: Save/load history to file
6. **Theme Switching**: Multiple built-in themes
7. **Customization**: User-configurable button layouts
8. **Multi-line Expressions**: Support for complex nested calculations

## Design Patterns Used

1. **MVC Pattern**: Model-View-Controller architecture
2. **Service Layer Pattern**: Separation of business logic
3. **Singleton Pattern**: ThemeManager utilities
4. **Strategy Pattern**: Different calculation operations
5. **Observer Pattern**: Event listeners for UI interactions

## Code Quality Metrics

- ✅ **Cohesion**: High - related functionalities grouped together
- ✅ **Coupling**: Low - minimal dependencies between modules
- ✅ **Maintainability**: High - clear naming and structure
- ✅ **Testability**: High - business logic separated from UI
- ✅ **Extensibility**: High - easy to add new features

## Performance Considerations

- **Instant Calculations**: No background threads needed for simple operations
- **Efficient History**: String-based history with efficient appending
- **Optimized Display**: Lazy formatting in display methods
- **Memory Efficient**: Minimal object allocation during calculations

## System Requirements

- **OS**: Windows, macOS, or Linux
- **Java**: JDK 8 or higher
- **RAM**: 128 MB minimum
- **Disk Space**: 5 MB minimum
- **Display**: 400×550 minimum resolution

## Troubleshooting

### Application Won't Start
**Issue**: "Error: could not find or load main class"
**Solution**: Ensure you compiled with `-d .` flag and are running from project root

### Buttons Not Working
**Issue**: UI displays but buttons don't respond
**Solution**: Check console for exceptions; may indicate event listener issues

### Incorrect Calculations
**Issue**: Results show unexpected values
**Solution**: Check MathUtil error handling; verify input parsing

### Display Overlapping
**Issue**: UI elements overlap when resizing
**Solution**: Adjust minimum window size or layout managers

## License

This project is provided as-is for educational and commercial use.

## Author Notes

This calculator demonstrates professional Java development practices:
- Clean architecture with clear separation of concerns
- Comprehensive error handling
- Modern Swing GUI development
- Production-quality code standards
- Extensive documentation

The application serves as an excellent template for building desktop applications in Java and can be easily extended with additional features or modified for specific use cases.

---

**Version**: 1.0  
**Last Updated**: March 2026  
**Status**: Production Ready
