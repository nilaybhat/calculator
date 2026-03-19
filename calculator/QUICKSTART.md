# Quick Start Guide - Professional Calculator

## 🚀 Get Started in 30 Seconds

### Windows
```bash
cd d:\calculator
build.bat
```

### Linux/macOS
```bash
cd calculator
chmod +x build.sh
./build.sh
```

### Manual Compilation
```bash
cd calculator
javac -d . src/config/AppConfig.java src/util/MathUtil.java src/model/CalculatorModel.java src/service/CalculatorService.java src/ui/ThemeManager.java src/ui/CalculatorUI.java src/main/Main.java
java main.Main
```

---

## 📋 Project Structure

```
calculator/
├── src/                          # Source code directory
│   ├── config/
│   │   └── AppConfig.java       # App configuration (50 lines)
│   ├── main/
│   │   └── Main.java            # Entry point (25 lines)
│   ├── model/
│   │   └── CalculatorModel.java # State management (250 lines)
│   ├── service/
│   │   └── CalculatorService.java # Business logic (150 lines)
│   ├── ui/
│   │   ├── CalculatorUI.java    # GUI implementation (350 lines)
│   │   └── ThemeManager.java    # UI theming (120 lines)
│   └── util/
│       └── MathUtil.java        # Math operations (90 lines)
│
├── config/                       # Compiled classes (generated)
├── main/                         # Compiled classes (generated)
├── model/                        # Compiled classes (generated)
├── service/                      # Compiled classes (generated)
├── ui/                           # Compiled classes (generated)
├── util/                         # Compiled classes (generated)
│
├── build.bat                     # Windows build script
├── build.sh                      # Linux/macOS build script
├── README.md                     # Full documentation (800+ lines)
├── QUICKSTART.md                 # This file
└── ARCHITECTURE.md               # Architecture details
```

---

## ⌨️ Calculator Operations

### Basic Math
| Operation | Button | Keyboard |
|-----------|--------|----------|
| Addition | + | + |
| Subtraction | - | - |
| Multiplication | × | * |
| Division | ÷ | / |
| Power | ^ | ^ |
| Modulus | % | % |

### Functions
| Function | Button | Result |
|----------|--------|--------|
| Square Root | √ | √x |
| Percentage | % (after equals) | x ÷ 100 |
| Negate | ± | -x |
| Decimal | . | x.y |

### Control
| Function | Button | Keyboard |
|----------|--------|----------|
| Calculate | = | Enter |
| Clear | C | Delete |
| Backspace | ← | Backspace |
| Memory Add | M+ | (click) |
| Memory Subtract | M- | (click) |
| Memory Recall | MR | (click) |
| Memory Clear | MC | (click) |
| Show History | History | (click) |
| Clear History | Clear | (click) |

---

## 💡 Example Calculations

```
5 + 3 = 8
10 - 4 = 6
7 * 6 = 42
20 / 4 = 5
2 ^ 3 = 8
√16 = 4
15 % 4 = 3
100 / 2 = 50
```

---

## 🎨 Dark Theme Features

- **Dark Background**: Easy on the eyes
- **Orange Accent**: Highlights equals button
- **Hover Effects**: Interactive feedback
- **Responsive**: Adapts to window size
- **Professional Look**: Modern desktop UI

---

## 🔧 System Requirements

| Requirement | Value |
|------------|-------|
| Java Version | JDK 8+ |
| Disk Space | 5 MB |
| RAM | 128 MB |
| Min Resolution | 400×550 |
| OS | Windows/Mac/Linux |

---

## ❓ Troubleshooting

### Command not found: javac
**Solution**: Install JDK and add to PATH

### "Could not find main class"
**Solution**: Ensure you're in the `calculator` directory when running

### Buttons not responding
**Solution**: Check terminal for error messages

### Window too small
**Solution**: Drag window corner to resize

---

## 📚 Architecture Overview

```
User Input
    ↓
🔲 CalculatorUI (Presentation Layer)
    ↓
📊 CalculatorService (Business Logic)
    ↓
💾 CalculatorModel (Data Layer)
    ↓
🔢 MathUtil (Utility Functions)
```

**Key Design Principles**:
- Separation of Concerns ✓
- Model-View-Controller ✓
- Service Layer Pattern ✓
- Error Handling ✓
- Input Validation ✓

---

## 🎯 Features Summary

✅ **8 Mathematical Operations**
- Addition, Subtraction, Multiplication, Division
- Power, Modulus, Square Root, Percentage

✅ **Memory Functions**
- M+ (Add to Memory)
- M- (Subtract from Memory)
- MR (Recall Memory)
- MC (Clear Memory)

✅ **User Friendly**
- Dark mode theme
- Keyboard support
- Calculation history
- Error handling
- Hover effects

✅ **Professional Code**
- Clean architecture
- Well documented
- Easy to extend
- Production ready
- 7 modular classes

---

## 🚀 Running the Application

### Method 1: Using Build Script (Recommended)
```bash
cd d:\calculator
build.bat
```

### Method 2: Manual Compilation
```bash
cd d:\calculator
javac -d . src/config/AppConfig.java src/util/MathUtil.java src/model/CalculatorModel.java src/service/CalculatorService.java src/ui/ThemeManager.java src/ui/CalculatorUI.java src/main/Main.java
java main.Main
```

### Method 3: Using IDE
1. Open project in IDE (IntelliJ, Eclipse, VS Code)
2. Build project
3. Run Main.java

---

## 📖 Full Documentation

See `README.md` for:
- Complete class documentation
- Architecture details
- Design patterns used
- Extension points
- Build troubleshooting

---

## 💻 Next Steps

1. ✅ Compile the application
2. ✅ Run and explore features
3. ✅ Try different calculations
4. ✅ Test keyboard input
5. ✅ Check memory functions
6. ✅ View calculation history

**Enjoy your Professional Calculator! 🎉**

---

*Version 1.0 | Production Ready | March 2026*
