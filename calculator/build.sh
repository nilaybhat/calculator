#!/bin/bash
# ========================================
# Professional Calculator Application
# Build and Run Script for Linux/macOS
# ========================================

echo ""
echo "========================================"
echo "  Professional Calculator Builder"
echo "========================================"
echo ""

if [ "$1" == "clean" ]; then
    echo "Cleaning compiled files..."
    rm -rf config/ main/ model/ service/ ui/ util/
    echo "Cleanup complete!"
    exit 0
fi

if [ "$1" == "run" ]; then
    echo "Starting Calculator Application..."
    java main.Main
    exit 0
fi

if [ "$1" == "" ] || [ "$1" == "build" ]; then
    echo "[1/2] Compiling Java source files..."
    javac -d . \
        src/config/AppConfig.java \
        src/util/MathUtil.java \
        src/model/CalculatorModel.java \
        src/service/CalculatorService.java \
        src/ui/ThemeManager.java \
        src/ui/CalculatorUI.java \
        src/main/Main.java
    
    if [ $? -ne 0 ]; then
        echo ""
        echo "[ERROR] Compilation failed!"
        exit 1
    fi
    
    echo "[2/2] Compilation successful!"
    echo ""
    echo "Running application..."
    java main.Main
    exit 0
fi

echo "Usage:"
echo "  ./build.sh          - Compile and run application"
echo "  ./build.sh run      - Run compiled application"
echo "  ./build.sh clean    - Remove all compiled files"
