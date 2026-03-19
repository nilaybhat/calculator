# Calculator Architecture Document

## System Architecture

### Layered Architecture Model

```
┌─────────────────────────────────────────────────┐
│           Presentation Layer (UI)               │
│   CalculatorUI + ThemeManager                   │
│   - GUI components                              │
│   - Event handling                              │
│   - User feedback                               │
└──────────────────┬──────────────────────────────┘
                   │ Delegates
                   ↓
┌─────────────────────────────────────────────────┐
│         Business Logic Layer (Service)          │
│   CalculatorService                             │
│   - Input validation                            │
│   - Operation orchestration                     │
│   - Error handling                              │
└──────────────────┬──────────────────────────────┘
                   │ Uses
                   ↓
┌─────────────────────────────────────────────────┐
│           Data Layer (Model)                    │
│   CalculatorModel                               │
│   - Calculator state                            │
│   - History management                          │
│   - Memory storage                              │
└──────────────────┬──────────────────────────────┘
                   │ Uses
                   ↓
┌─────────────────────────────────────────────────┐
│         Utility Layer (Functions)               │
│   MathUtil + AppConfig                          │
│   - Mathematical operations                     │
│   - Configuration                               │
│   - Helper functions                            │
└─────────────────────────────────────────────────┘
```

## Component Interaction

### Calculator Operation Flow

```
User (Button Click / Keyboard)
        ↓
   CalculatorUI
   (Event Handler)
        ↓
CalculatorService
(Validates Input)
        ↓
  Multiple Paths:
  
  Path 1: Number Input
  ├─→ CalculatorModel.appendDigit()
  └─→ Return formatted display
  
  Path 2: Arithmetic Operation
  ├─→ CalculatorModel.setOperation()
  └─→ Return display
  
  Path 3: Calculate
  ├─→ MathUtil.perform*()
  ├─→ CalculatorModel.calculate()
  ├─→ Update history
  └─→ Return result
  
  Path 4: Advanced Function
  ├─→ MathUtil.squareRoot/power/etc()
  └─→ Return result
        ↓
   CalculatorUI
   (Update Display)
        ↓
     User Sees Result
```

## Class Responsibilities Matrix

| Class | Responsibility | Dependencies |
|-------|-----------------|--------------|
| **Main** | Application startup | CalculatorUI |
| **AppConfig** | Configuration values | None (Utility) |
| **MathUtil** | Mathematical operations | AppConfig |
| **CalculatorModel** | State management | MathUtil |
| **CalculatorService** | Business logic | CalculatorModel, MathUtil |
| **ThemeManager** | UI styling | AppConfig |
| **CalculatorUI** | Graphical interface | CalculatorService, ThemeManager, AppConfig |

## Data Flow Diagram

```
INPUT
  │
  ├─ Number (0-9)
  │   └─→ Append to current input
  │
  ├─ Operation (+, -, *, /, %, ^)
  │   ├─→ Store previous value
  │   ├─→ Store operation
  │   └─→ Reset input flag
  │
  ├─ Equals (=)
  │   ├─→ Get both operands
  │   ├─→ Call MathUtil.perform()
  │   ├─→ Store result
  │   ├─→ Add to history
  │   └─→ Reset state
  │
  ├─ Special Function (√, %)
  │   ├─→ Get current value
  │   ├─→ Apply function
  │   └─→ Return result
  │
  ├─ Memory (M+, M-, MR, MC)
  │   └─→ Update memory state
  │
  ├─ Clear/Backspace
  │   └─→ Reset/modify input
  │
  └─ Display Update
      └─→ Format and show

OUTPUT
  │
  ├─ Display field → User sees calculation
  ├─ History panel → User sees past calculations
  └─ Memory label → User sees memory value
```

## Error Handling Strategy

```
User Input
    ↓
Validate Input
    ├─ Valid?
    │   ├─ YES → Process
    │   └─ NO → Show "Error"
    ↓
Process Operation
    ├─ Division by zero?
    │   ├─ YES → Show "Error"
    │   └─ NO → Calculate
    ├─ Negative square root?
    │   ├─ YES → Show "Error"
    │   └─ NO → Calculate
    ├─ Invalid modulus?
    │   ├─ YES → Show "Error"
    │   └─ NO → Calculate
    ↓
Return Result
    └─ Format and display
```

## State Management

```
CalculatorModel State:
├── currentValue: double
├── previousValue: double
├── currentInput: String
├── operation: String (or null)
├── memory: double
├── history: StringBuilder
└── shouldResetDisplay: boolean

Lifecycle:
1. Initialize: All zeros/empty
2. On number input: Update currentInput
3. On operation: Store currentValue, set operation
4. On equals: Calculate and reset
5. On clear: Reset all to defaults
```

## Memory Management

```
Memory Operations:
├─ M+ (Memory Add)
│   └─→ memory = memory + currentValue
├─ M- (Memory Subtract)
│   └─→ memory = memory - currentValue
├─ MR (Memory Recall)
│   └─→ Move memory value to display
└─ MC (Memory Clear)
    └─→ memory = 0

Memory Persistence:
└─ Maintained in CalculatorModel
   └─ Lost on application exit (by design)
```

## Keyboard Event Mapping

```
Key Input → Event Listener → Handler

Number Keys (0-9)
    └─→ inputNumber(key)

Operator Keys (+, -, *, /)
    └─→ inputOperation(operator)

Decimal (.)
    └─→ inputDecimal()

Enter / =
    └─→ calculate()

Backspace
    └─→ backspace()

Delete
    └─→ clear()

Mathematical Operators
├─ * → × (multiplication)
├─ / → ÷ (division)
└─ ^ → ^ (power)
```

## Thread Safety

```
Event Dispatch Thread (EDT):
├─ All UI updates run on EDT
├─ Calculator operations are synchronous
├─ No background threads used
└─ Safe operation guaranteed

Design:
└─ SwingUtilities.invokeLater()
   └─→ Ensures EDT safety
```

## Performance Characteristics

Operation | Time | Space | Notes
----------|------|-------|-------
Number Input | O(1) | O(1) | Append to string
Operation Set | O(1) | O(1) | Store values
Calculate | O(1) | O(1) | Single operation
Square Root | O(1) | O(1) | Math.sqrt()
History Store | O(n) | O(n) | String concatenation
Display Format | O(1) | O(1) | Number formatting

## Extensibility Points

### 1. Add New Mathematical Operation

```java
// Step 1: Add to MathUtil
public static double newOperation(double a, double b) {
    return result;
}

// Step 2: Add case in CalculatorService
case "operator":
    return divide(a, operand);

// Step 3: Add button in CalculatorUI
String[][] buttons = {
    {"operator", ...}
}
```

### 2. Add New Theme

```java
// Step 1: Add colors to AppConfig
public static final String COLOR_NEW = "#hexcode";

// Step 2: Create theme method in ThemeManager
public static void applyNewTheme() {}

// Step 3: Call in CalculatorUI
ThemeManager.applyNewTheme();
```

### 3. Add Persistence

```java
// Serialize model to JSON/XML
// Load on startup
// No changes to UI layer
```

### 4. Add Scientific Functions

```java
// Add to MathUtil: sin(), cos(), tan(), log(), ln()
// Add to CalculatorUI: new buttons
// Add to CalculatorService: new cases
```

## Design Patterns Used

| Pattern | Location | Purpose |
|---------|----------|---------|
| **MVC** | UI + Service + Model | Separation of concerns |
| **Service Layer** | CalculatorService | Business logic isolation |
| **Singleton** | ThemeManager, AppConfig | Centralized management |
| **Strategy** | MathUtil operations | Algorithm selection |
| **Observer** | Event listeners | UI responsiveness |
| **Factory** | Color creation | Object construction |

## Code Quality Metrics

```
Metric | Value | Status
-------|-------|--------
Cohesion | High | ✓ Good
Coupling | Low | ✓ Good
Maintainability | High | ✓ Good
Testability | High | ✓ Good
Cyclomatic Complexity | Low | ✓ Good
Code Duplication | Minimal | ✓ Good
Comments/Javadoc | Comprehensive | ✓ Good
```

## Testing Strategy

```
Unit Tests (Not included):
├─ MathUtil operations
├─ CalculatorModel state
└─ CalculatorService logic

Integration Tests (Manual):
├─ UI event handling
├─ Display updates
└─ History tracking

User Acceptance Tests:
├─ All operations work
├─ Error handling works
├─ Keyboard input works
└─ Memory functions work
```

## Deployment Considerations

```
Prerequisites:
├─ JDK 8+ installed
├─ Java in system PATH
└─ 400×550 minimum resolution

Distribution:
├─ Source files (src/)
├─ Build scripts (build.bat, build.sh)
├─ Documentation (README.md)
└─ Compiled classes (auto-generated)

Execution:
├─ Compile locally
├─ Run: java main.Main
└─ Or use build script
```

## Security Considerations

```
Input Validation:
├─ Only digits 0-9 accepted
├─ Only valid operators accepted
└─ Invalid input → "Error"

Division by Zero:
├─ Checked before division
└─ Throws ArithmeticException

Square Root:
├─ Validates non-negative
└─ Throws on negative input

Memory Safety:
├─ No external file access
├─ No network communication
└─ Fully sandboxed
```

---

**Document Version**: 1.0  
**Status**: Complete  
**Last Updated**: March 2026
