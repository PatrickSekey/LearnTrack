@echo off
echo ========================================
echo    LearnTrack - Student Management System
echo ========================================
echo.

echo Step 1: Creating output directory...
if not exist "out" mkdir out

echo Step 2: Compiling Java files...
javac -d out -encoding UTF8 ^
    src\com\airtribe\learntrack\entity\*.java ^
    src\com\airtribe\learntrack\service\*.java ^
    src\com\airtribe\learntrack\ui\*.java ^
    src\com\airtribe\learntrack\exception\*.java ^
    src\com\airtribe\learntrack\util\*.java

if %ERRORLEVEL% EQU 0 (
    echo.
    echo Compilation successful!
    echo.
    echo Step 3: Running LearnTrack...
    echo ========================================
    java -cp out com.airtribe.learntrack.ui.Main
) else (
    echo.
    echo Compilation failed! Please check for errors.
    pause
)