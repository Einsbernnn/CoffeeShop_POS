# Product Images Feature

## Overview
The POS system now supports product images for all menu items. Each product can have a custom image that is displayed in the product selection interface.

## How It Works

### Automatic Image Loading
- Product images are automatically loaded from the `images/products/` directory
- Image filenames should match the product name (lowercase, spaces replaced with underscores)
- Example: "Espresso" product → `images/products/espresso.jpg`

### Image Requirements
- **Format**: JPG, PNG, or other common image formats
- **Size**: Recommended 200x200 pixels or larger (will be automatically resized to 80x80 for display)
- **Naming**: Use lowercase with underscores for spaces
  - "Caramel Latte" → `caramel_latte.jpg`
  - "Hot Chocolate" → `hot_chocolate.jpg`

### Default Placeholder Images
If a product image is not found, the system automatically creates a placeholder image with:
- Category-specific background color
- Category icon (☕ for coffee, 🍵 for tea, etc.)
- Product name displayed

## Adding Custom Images

### Method 1: Replace Placeholder Images
1. Navigate to the `images/products/` directory
2. Replace any placeholder image with your custom image
3. Use the same filename (e.g., `espresso.jpg`)

### Method 2: Add New Product Images
1. Place your image in the `images/products/` directory
2. Name it according to the product name convention
3. The system will automatically detect and load it

### Method 3: Programmatically
You can also set custom image paths in the Product class:

```java
Product product = new Product("Custom Product", 100, "Coffee", "Description", "custom/path/image.jpg");
```

## Image Categories and Colors

| Category | Background Color | Icon |
|----------|-----------------|------|
| Coffee   | Brown (#8B4513) | ☕ |
| Tea      | Green (#228B22) | 🍵 |
| Food     | Gold (#FFD700)  | 🥐 |
| Desserts | Pink (#FF1493)  | 🍰 |
| Drinks   | Sky Blue (#87CEEB) | 🥤 |

## Technical Details

### Image Processing
- Images are automatically resized to 80x80 pixels for consistent display
- Uses high-quality scaling for smooth appearance
- Supports transparency and various image formats

### Performance
- Images are loaded once when the product is created
- Cached in memory for fast access
- Automatic fallback to placeholder if loading fails

### File Structure
```
MyPOS/
├── images/
│   └── products/
│       ├── espresso.jpg
│       ├── latte.jpg
│       ├── cappuccino.jpg
│       └── ...
└── src/
    └── Product.java (handles image loading)
```

## Troubleshooting

### Image Not Displaying
1. Check that the image file exists in `images/products/`
2. Verify the filename matches the product name (lowercase, underscores)
3. Ensure the image format is supported (JPG, PNG, etc.)

### Custom Image Paths
If you need to use images from a different location, you can modify the `imagePath` in the Product constructor or use the setter method:

```java
product.setImagePath("custom/path/to/image.jpg");
```

## Future Enhancements
- Support for multiple image sizes
- Image upload interface in the admin panel
- Web-based image management
- Image compression and optimization 