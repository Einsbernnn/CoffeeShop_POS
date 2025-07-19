import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<String, Integer> stockLevels;
    private Map<String, Integer> minimumStockLevels;
    
    public Inventory() {
        this.stockLevels = new HashMap<>();
        this.minimumStockLevels = new HashMap<>();
        initializeDefaultInventory();
    }
    
    private void initializeDefaultInventory() {
        // Coffee shop inventory items
        addItem("Coffee Beans", 100, 20);
        addItem("Milk", 50, 10);
        addItem("Sugar", 200, 30);
        addItem("Cups", 500, 50);
        addItem("Espresso Shots", 200, 25);
        addItem("Whipped Cream", 30, 5);
        addItem("Caramel Syrup", 20, 3);
        addItem("Vanilla Syrup", 20, 3);
        addItem("Chocolate Syrup", 20, 3);
        addItem("Tea Bags", 150, 20);
        addItem("Ice", 1000, 100);
        addItem("Napkins", 1000, 100);
    }
    
    public void addItem(String itemName, int quantity, int minimumStock) {
        stockLevels.put(itemName, quantity);
        minimumStockLevels.put(itemName, minimumStock);
    }
    
    public void updateStock(String itemName, int quantity) {
        stockLevels.put(itemName, quantity);
    }
    
    public void addStock(String itemName, int quantity) {
        int currentStock = stockLevels.getOrDefault(itemName, 0);
        stockLevels.put(itemName, currentStock + quantity);
    }
    
    public void removeStock(String itemName, int quantity) {
        int currentStock = stockLevels.getOrDefault(itemName, 0);
        int newStock = Math.max(0, currentStock - quantity);
        stockLevels.put(itemName, newStock);
    }
    
    public int getStock(String itemName) {
        return stockLevels.getOrDefault(itemName, 0);
    }
    
    public int getMinimumStock(String itemName) {
        return minimumStockLevels.getOrDefault(itemName, 0);
    }
    
    public boolean isLowStock(String itemName) {
        int currentStock = getStock(itemName);
        int minimumStock = getMinimumStock(itemName);
        return currentStock <= minimumStock;
    }
    
    public boolean isOutOfStock(String itemName) {
        return getStock(itemName) <= 0;
    }
    
    public Map<String, Integer> getLowStockItems() {
        Map<String, Integer> lowStockItems = new HashMap<>();
        for (String item : stockLevels.keySet()) {
            if (isLowStock(item)) {
                lowStockItems.put(item, getStock(item));
            }
        }
        return lowStockItems;
    }
    
    public Map<String, Integer> getAllStock() {
        return new HashMap<>(stockLevels);
    }
    
    public void setMinimumStock(String itemName, int minimumStock) {
        minimumStockLevels.put(itemName, minimumStock);
    }
} 