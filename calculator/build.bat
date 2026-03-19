@echo off
REM ========================================
REM Professional Calculator Application
REM Build and Run Script for Windows
REM ========================================

echo.
echo ========================================
echo  Professional Calculator Builder
echo ========================================
echo.

if "%1"=="clean" goto clean
if "%1"=="run" goto run
if "%1"=="" goto build

goto help

:build
echo [1/2] Compiling Java source files...
javac -d . ^
    src\config\AppConfig.java ^
    src\util\MathUtil.java ^
    src\model\CalculatorModel.java ^
    src\service\CalculatorService.java ^
    src\ui\ThemeManager.java ^
    src\ui\CalculatorUI.java ^
    src\main\Main.java

if %errorlevel% neq 0 (
    echo.
    echo [ERROR] Compilation failed!
    pause
    exit /b 1
)

echo [2/2] Compilation successful!
echo.
echo Running application...
java main.Main
goto end

:run
echo Starting Calculator Application...
java main.Main
goto end

:clean
echo Cleaning compiled files...
if exist config rmdir /s /q config
if exist main rmdir /s /q main
if exist model rmdir /s /q model
if exist service rmdir /s /q service
if exist ui rmdir /s /q ui
if exist util rmdir /s /q util
echo Cleanup complete!
goto end

:help
echo Usage:
echo   build.bat          - Compile and run application
echo   build.bat run      - Run compiled application
echo   build.bat clean    - Remove all compiled files
goto end

:end
echo.
pause
