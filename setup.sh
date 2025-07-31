#!/bin/bash

# Coffee Shop POS System Setup Script
# This script automates the setup process for the POS system

echo "☕ Coffee Shop POS System Setup"
echo "================================"

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "❌ Java is not installed. Please install Java 8 or higher."
    echo "Download from: https://www.oracle.com/java/technologies/downloads/"
    exit 1
fi

# Check Java version
JAVA_VERSION=$(java -version 2>&1 | head -n 1 | cut -d'"' -f2 | cut -d'.' -f1-2)
echo "✅ Java version: $JAVA_VERSION"

# Navigate to src directory
cd src

# Compile all Java files
echo "🔨 Compiling Java files..."
if javac *.java; then
    echo "✅ Compilation successful!"
else
    echo "❌ Compilation failed. Please check for errors."
    exit 1
fi

# Generate product images
echo "🖼️  Generating product images..."
if java GenerateProductImages; then
    echo "✅ Product images generated successfully!"
else
    echo "⚠️  Warning: Could not generate product images. Continuing..."
fi

# Move images to correct location if they were created in src
if [ -d "images" ]; then
    echo "📁 Moving images to correct location..."
    mv images/products/* ../images/products/ 2>/dev/null || true
    rm -rf images 2>/dev/null || true
fi

# Test the application
echo "🧪 Testing application..."
if java POSLogin &> /dev/null & then
    echo "✅ Application started successfully!"
    echo "💡 Close the application window to continue setup."
    sleep 3
    pkill -f "java POSLogin" 2>/dev/null || true
else
    echo "❌ Application failed to start. Please check for errors."
    exit 1
fi

echo ""
echo "🎉 Setup completed successfully!"
echo ""
echo "📋 Next steps:"
echo "1. Run: cd src && java POSLogin"
echo "2. Login with default credentials (see README.md)"
echo "3. Customize product images in images/products/"
echo "4. Configure RFID reader if needed"
echo ""
echo "📖 For detailed instructions, see README.md"
echo "🆘 For help, check the troubleshooting section in README.md" 