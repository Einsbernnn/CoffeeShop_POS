public class Product {
    private String name;
    private double basePrice;
    private String category;
    private String description;
    private boolean available;
    
    public Product(String name, double basePrice, String category, String description) {
        this.name = name;
        this.basePrice = basePrice;
        this.category = category;
        this.description = description;
        this.available = true;
    }
    
    // Getters
    public String getName() { return name; }
    public double getBasePrice() { return basePrice; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }
    public boolean isAvailable() { return available; }
    
    // Setters
    public void setName(String name) { this.name = name; }
    public void setBasePrice(double basePrice) { this.basePrice = basePrice; }
    public void setCategory(String category) { this.category = category; }
    public void setDescription(String description) { this.description = description; }
    public void setAvailable(boolean available) { this.available = available; }
    
    @Override
    public String toString() {
        return name + " - ₱" + String.format("%.2f", basePrice);
    }
} 