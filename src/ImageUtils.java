import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageUtils {
    
    /**
     * Creates a placeholder image for a product
     */
    public static void createPlaceholderImage(String productName, String category, String outputPath) {
        BufferedImage image = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        
        // Set background color based on category
        Color bgColor = getCategoryColor(category);
        g2d.setColor(bgColor);
        g2d.fillRect(0, 0, 200, 200);
        
        // Draw border
        g2d.setColor(bgColor.darker());
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRect(5, 5, 190, 190);
        
        // Draw category icon
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 48));
        String icon = getCategoryIcon(category);
        FontMetrics fm = g2d.getFontMetrics();
        int iconX = (200 - fm.stringWidth(icon)) / 2;
        int iconY = 80;
        g2d.drawString(icon, iconX, iconY);
        
        // Draw product name
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        fm = g2d.getFontMetrics();
        String[] words = productName.split(" ");
        int y = 130;
        for (String word : words) {
            int x = (200 - fm.stringWidth(word)) / 2;
            g2d.drawString(word, x, y);
            y += 20;
        }
        
        g2d.dispose();
        
        // Save the image
        try {
            File outputFile = new File(outputPath);
            outputFile.getParentFile().mkdirs();
            ImageIO.write(image, "jpg", outputFile);
        } catch (IOException e) {
            System.err.println("Error saving placeholder image: " + e.getMessage());
        }
    }
    
    private static Color getCategoryColor(String category) {
        switch (category.toLowerCase()) {
            case "coffee": return new Color(139, 69, 19); // Brown
            case "tea": return new Color(34, 139, 34); // Green
            case "food": return new Color(255, 215, 0); // Gold
            case "desserts": return new Color(255, 20, 147); // Pink
            case "drinks": return new Color(135, 206, 235); // Sky blue
            default: return new Color(128, 128, 128); // Gray
        }
    }
    
    private static String getCategoryIcon(String category) {
        switch (category.toLowerCase()) {
            case "coffee": return "☕";
            case "tea": return "🍵";
            case "food": return "🥐";
            case "desserts": return "🍰";
            case "drinks": return "🥤";
            default: return "📦";
        }
    }
    
    /**
     * Creates placeholder images for all default products
     */
    public static void createAllPlaceholderImages() {
        String[][] products = {
            // Coffee products
            {"Espresso", "Coffee"},
            {"Americano", "Coffee"},
            {"Latte", "Coffee"},
            {"Cappuccino", "Coffee"},
            {"Mocha", "Coffee"},
            {"Macchiato", "Coffee"},
            {"Flat White", "Coffee"},
            {"Affogato", "Coffee"},
            {"Cold Brew", "Coffee"},
            {"Iced Latte", "Coffee"},
            {"Caramel Latte", "Coffee"},
            {"Matcha Latte", "Coffee"},
            
            // Tea products
            {"Green Tea", "Tea"},
            {"Black Tea", "Tea"},
            {"Chai Latte", "Tea"},
            {"Earl Grey", "Tea"},
            {"Chamomile", "Tea"},
            {"Peppermint", "Tea"},
            {"Jasmine Tea", "Tea"},
            {"Oolong Tea", "Tea"},
            {"Rooibos", "Tea"},
            {"Lemon Tea", "Tea"},
            {"Honey Tea", "Tea"},
            {"Iced Tea", "Tea"},
            
            // Food products
            {"Croissant", "Food"},
            {"Danish", "Food"},
            {"Muffin", "Food"},
            {"Bagel", "Food"},
            {"Sandwich", "Food"},
            {"Toast", "Food"},
            {"Cookie", "Food"},
            {"Brownie", "Food"},
            {"Scone", "Food"},
            {"Panini", "Food"},
            {"Quiche", "Food"},
            {"Salad", "Food"},
            
            // Dessert products
            {"Cheesecake", "Desserts"},
            {"Tiramisu", "Desserts"},
            {"Chocolate Cake", "Desserts"},
            {"Apple/Mango Pie", "Desserts"},
            {"Ice Cream", "Desserts"},
            {"Pudding", "Desserts"},
            {"Cupcake", "Desserts"},
            {"Donut", "Desserts"},
            {"Macaron", "Desserts"},
            {"Eclair", "Desserts"},
            {"Cannoli", "Desserts"},
            {"Fruit Tart", "Desserts"},
            
            // Drink products
            {"Hot Tsokolate", "Drinks"},
            {"Smoothie", "Drinks"},
            {"Fruit Flavorred Juice", "Drinks"},
            {"Carbonated Sodas", "Drinks"},
            {"Bottled Water", "Drinks"},
            {"Milk", "Drinks"},
            {"Lemonade", "Drinks"},
            {"Hot Tea", "Drinks"},
            {"Energy Drink", "Drinks"},
            {"Milkshake", "Drinks"},
            {"Coconut Water", "Drinks"},
            {"Sparkling Water", "Drinks"}
        };
        
        for (String[] product : products) {
            String name = product[0];
            String category = product[1];
            String imagePath = "images/products/" + name.toLowerCase().replaceAll("\\s+", "_") + ".jpg";
            createPlaceholderImage(name, category, imagePath);
        }
        
        System.out.println("Created placeholder images for all products!");
    }
} 