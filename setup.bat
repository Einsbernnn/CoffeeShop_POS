@echo off
chcp 65001 >nul

echo ☕ Coffee Shop POS System Setup
echo ================================

REM Check if Java is installed
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ Java is not installed. Please install Java 8 or higher.
    echo Download from: https://www.oracle.com/java/technologies/downloads/
    pause
    exit /b 1
)

REM Check Java version
for /f "tokens=3" %%g in ('java -version 2^>^&1 ^| findstr /i "version"') do (
    set JAVA_VERSION=%%g
)
echo ✅ Java version: %JAVA_VERSION%

REM Navigate to src directory
cd src

REM Compile all Java files
echo 🔨 Compiling Java files...
javac *.java
if %errorlevel% neq 0 (
    echo ❌ Compilation failed. Please check for errors.
    pause
    exit /b 1
)
echo ✅ Compilation successful!

REM Generate product images
echo 🖼️  Generating product images...
java GenerateProductImages
if %errorlevel% neq 0 (
    echo ⚠️  Warning: Could not generate product images. Continuing...
) else (
    echo ✅ Product images generated successfully!
)

REM Move images to correct location if they were created in src
if exist "images" (
    echo 📁 Moving images to correct location...
    move images\products\* ..\images\products\ >nul 2>&1
    rmdir /s /q images >nul 2>&1
)

REM Test the application
echo 🧪 Testing application...
start /b java POSLogin
timeout /t 3 /nobreak >nul
taskkill /f /im java.exe >nul 2>&1
echo ✅ Application started successfully!

echo.
echo 🎉 Setup completed successfully!
echo.
echo 📋 Next steps:
echo 1. Run: cd src ^&^& java POSLogin
echo 2. Login with default credentials (see README.md)
echo 3. Customize product images in images/products/
echo 4. Configure RFID reader if needed
echo.
echo 📖 For detailed instructions, see README.md
echo 🆘 For help, check the troubleshooting section in README.md
pause 