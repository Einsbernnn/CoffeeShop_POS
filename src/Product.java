import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Product {
    private String name;
    private double basePrice;
    private String category;
    private String description;
    private boolean available;
    private String imagePath;
    private ImageIcon imageIcon;
    
    public Product(String name, double basePrice, String category, String description) {
        this.name = name;
        this.basePrice = basePrice;
        this.category = category;
        this.description = description;
        this.available = true;
        this.imagePath = "images/products/" + name.toLowerCase().replaceAll("\\s+", "_") + ".jpg";
        loadImage();
    }
    
    public Product(String name, double basePrice, String category, String description, String imagePath) {
        this.name = name;
        this.basePrice = basePrice;
        this.category = category;
        this.description = description;
        this.available = true;
        this.imagePath = imagePath;
        loadImage();
    }
    
    private void loadImage() {
        try {
            File imageFile = new File(imagePath);
            if (imageFile.exists()) {
                BufferedImage originalImage = ImageIO.read(imageFile);
                // Resize image to 80x80 pixels for consistent display
                Image resizedImage = originalImage.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(resizedImage);
            } else {
                // Create a default placeholder image
                imageIcon = createDefaultImage();
            }
        } catch (IOException e) {
            // Create a default placeholder image if loading fails
            imageIcon = createDefaultImage();
        }
    }
    
    private ImageIcon createDefaultImage() {
        BufferedImage defaultImage = new BufferedImage(80, 80, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = defaultImage.createGraphics();
        
        // Set background color based on category
        Color bgColor = getCategoryColor();
        g2d.setColor(bgColor);
        g2d.fillRect(0, 0, 80, 80);
        
        // Draw a simple icon based on category
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 12));
        String icon = getCategoryIcon();
        FontMetrics fm = g2d.getFontMetrics();
        int x = (80 - fm.stringWidth(icon)) / 2;
        int y = (80 + fm.getAscent()) / 2;
        g2d.drawString(icon, x, y);
        
        g2d.dispose();
        return new ImageIcon(defaultImage);
    }
    
    private Color getCategoryColor() {
        switch (category.toLowerCase()) {
            case "coffee": return new Color(139, 69, 19); // Brown
            case "tea": return new Color(34, 139, 34); // Green
            case "food": return new Color(255, 215, 0); // Gold
            case "desserts": return new Color(255, 20, 147); // Pink
            case "drinks": return new Color(135, 206, 235); // Sky blue
            default: return new Color(128, 128, 128); // Gray
        }
    }
    
    private String getCategoryIcon() {
        switch (category.toLowerCase()) {
            case "coffee": return "☕";
            case "tea": return "🍵";
            case "food": return "🥐";
            case "desserts": return "🍰";
            case "drinks": return "🥤";
            default: return "📦";
        }
    }
    
    // Getters
    public String getName() { return name; }
    public double getBasePrice() { return basePrice; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }
    public boolean isAvailable() { return available; }
    public String getImagePath() { return imagePath; }
    public ImageIcon getImageIcon() { return imageIcon; }
    
    // Setters
    public void setName(String name) { this.name = name; }
    public void setBasePrice(double basePrice) { this.basePrice = basePrice; }
    public void setCategory(String category) { this.category = category; }
    public void setDescription(String description) { this.description = description; }
    public void setAvailable(boolean available) { this.available = available; }
    public void setImagePath(String imagePath) { 
        this.imagePath = imagePath; 
        loadImage();
    }
    
    @Override
    public String toString() {
        return name + " - ₱" + String.format("%.2f", basePrice);
    }
} 