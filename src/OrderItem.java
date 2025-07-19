public class OrderItem {
    private String productName;
    private String customization;
    private int quantity;
    private double unitPrice;
    
    public OrderItem() {}
    
    public OrderItem(String productName, String customization, int quantity, double unitPrice) {
        this.productName = productName;
        this.customization = customization;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
    
    // Getters
    public String getProductName() { return productName; }
    public String getCustomization() { return customization; }
    public int getQuantity() { return quantity; }
    public double getUnitPrice() { return unitPrice; }
    
    // Setters
    public void setProductName(String productName) { this.productName = productName; }
    public void setCustomization(String customization) { this.customization = customization; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }
    
    // Business methods
    public double getTotalPrice() {
        return unitPrice * quantity;
    }
    
    @Override
    public String toString() {
        return productName + " x" + quantity + " - ₱" + String.format("%.2f", getTotalPrice());
    }
} 