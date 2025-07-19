import java.util.*;

public class ProductManager {
    private static ProductManager instance;
    private Map<String, Product> products;
    private Map<String, List<Product>> productsByCategory;
    
    private ProductManager() {
        products = new HashMap<>();
        productsByCategory = new HashMap<>();
        initializeDefaultProducts();
    }
    
    public static ProductManager getInstance() {
        if (instance == null) {
            instance = new ProductManager();
        }
        return instance;
    }
    
    private void initializeDefaultProducts() {
        // Coffee products
        addProduct(new Product("Espresso", 60, "Coffee", "Strong single shot"));
        addProduct(new Product("Americano", 80, "Coffee", "Espresso + hot water"));
        addProduct(new Product("Latte", 100, "Coffee", "Espresso + steamed milk"));
        addProduct(new Product("Cappuccino", 100, "Coffee", "Equal parts espresso, milk, foam"));
        addProduct(new Product("Mocha", 120, "Coffee", "Espresso + chocolate + milk"));
        addProduct(new Product("Macchiato", 110, "Coffee", "Espresso with milk foam"));
        addProduct(new Product("Flat White", 105, "Coffee", "Espresso + microfoam"));
        addProduct(new Product("Affogato", 130, "Coffee", "Espresso + vanilla ice cream"));
        addProduct(new Product("Cold Brew", 90, "Coffee", "12-hour cold extraction"));
        addProduct(new Product("Iced Latte", 100, "Coffee", "Latte over ice"));
        addProduct(new Product("Caramel Latte", 120, "Coffee", "Latte + caramel syrup"));
        addProduct(new Product("Matcha Latte", 110, "Coffee", "Matcha + steamed milk"));
        
        // Tea products
        addProduct(new Product("Green Tea", 50, "Tea", "Traditional green tea"));
        addProduct(new Product("Black Tea", 50, "Tea", "Strong black tea"));
        addProduct(new Product("Chai Latte", 90, "Tea", "Spiced tea + milk"));
        addProduct(new Product("Earl Grey", 60, "Tea", "Bergamot black tea"));
        addProduct(new Product("Chamomile", 50, "Tea", "Calming herbal tea"));
        addProduct(new Product("Peppermint", 50, "Tea", "Refreshing mint tea"));
        addProduct(new Product("Jasmine Tea", 60, "Tea", "Fragrant jasmine green"));
        addProduct(new Product("Oolong Tea", 70, "Tea", "Semi-fermented tea"));
        addProduct(new Product("Rooibos", 55, "Tea", "South African red tea"));
        addProduct(new Product("Lemon Tea", 55, "Tea", "Black tea + lemon"));
        addProduct(new Product("Honey Tea", 65, "Tea", "Tea with honey"));
        addProduct(new Product("Iced Tea", 60, "Tea", "Cold brewed tea"));
        
        // Food products
        addProduct(new Product("Croissant", 80, "Food", "Buttery French pastry"));
        addProduct(new Product("Danish", 90, "Food", "Sweet filled pastry"));
        addProduct(new Product("Muffin", 70, "Food", "Blueberry or chocolate"));
        addProduct(new Product("Bagel", 60, "Food", "Toasted with cream cheese"));
        addProduct(new Product("Sandwich", 120, "Food", "Ham & cheese or veggie"));
        addProduct(new Product("Toast", 50, "Food", "Buttered or jam"));
        addProduct(new Product("Cookie", 40, "Food", "Chocolate chip or oatmeal"));
        addProduct(new Product("Brownie", 60, "Food", "Chocolate brownie"));
        addProduct(new Product("Scone", 75, "Food", "Traditional English scone"));
        addProduct(new Product("Panini", 130, "Food", "Grilled sandwich"));
        addProduct(new Product("Quiche", 140, "Food", "Savory egg pie"));
        addProduct(new Product("Salad", 150, "Food", "Fresh garden salad"));
        
        // Dessert products
        addProduct(new Product("Cheesecake", 180, "Desserts", "New York style"));
        addProduct(new Product("Tiramisu", 200, "Desserts", "Italian coffee dessert"));
        addProduct(new Product("Chocolate Cake", 160, "Desserts", "Rich chocolate layer"));
        addProduct(new Product("Apple Pie", 140, "Desserts", "Traditional apple pie"));
        addProduct(new Product("Ice Cream", 120, "Desserts", "Vanilla, chocolate, strawberry"));
        addProduct(new Product("Pudding", 100, "Desserts", "Chocolate or vanilla"));
        addProduct(new Product("Cupcake", 80, "Desserts", "Vanilla or chocolate"));
        addProduct(new Product("Donut", 70, "Desserts", "Glazed or filled"));
        addProduct(new Product("Macaron", 90, "Desserts", "French almond cookie"));
        addProduct(new Product("Eclair", 110, "Desserts", "Chocolate filled pastry"));
        addProduct(new Product("Cannoli", 130, "Desserts", "Italian cream filled"));
        addProduct(new Product("Fruit Tart", 150, "Desserts", "Fresh fruit tart"));
        
        // Drink products
        addProduct(new Product("Hot Chocolate", 90, "Drinks", "Rich chocolate drink"));
        addProduct(new Product("Smoothie", 120, "Drinks", "Fruit smoothie"));
        addProduct(new Product("Juice", 80, "Drinks", "Fresh orange or apple"));
        addProduct(new Product("Soda", 60, "Drinks", "Coke, Sprite, Fanta"));
        addProduct(new Product("Water", 30, "Drinks", "Bottled water"));
        addProduct(new Product("Milk", 50, "Drinks", "Cold milk"));
        addProduct(new Product("Lemonade", 70, "Drinks", "Fresh lemonade"));
        addProduct(new Product("Hot Tea", 50, "Drinks", "Various tea options"));
        addProduct(new Product("Energy Drink", 100, "Drinks", "Red Bull or Monster"));
        addProduct(new Product("Milkshake", 130, "Drinks", "Chocolate or vanilla"));
        addProduct(new Product("Coconut Water", 90, "Drinks", "Fresh coconut water"));
        addProduct(new Product("Sparkling Water", 70, "Drinks", "Carbonated water"));
    }
    
    public void addProduct(Product product) {
        products.put(product.getName(), product);
        
        // Add to category list
        productsByCategory.computeIfAbsent(product.getCategory(), k -> new ArrayList<>()).add(product);
    }
    
    public Product getProduct(String name) {
        return products.get(name);
    }
    
    public List<Product> getProductsByCategory(String category) {
        return productsByCategory.getOrDefault(category, new ArrayList<>());
    }
    
    public List<String> getCategories() {
        return new ArrayList<>(productsByCategory.keySet());
    }
    
    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }
    
    public boolean removeProduct(String name) {
        Product product = products.remove(name);
        if (product != null) {
            List<Product> categoryProducts = productsByCategory.get(product.getCategory());
            if (categoryProducts != null) {
                categoryProducts.remove(product);
            }
            return true;
        }
        return false;
    }
    
    public void updateProduct(String name, Product updatedProduct) {
        if (products.containsKey(name)) {
            removeProduct(name);
            addProduct(updatedProduct);
        }
    }
    
    public List<Product> searchProducts(String query) {
        List<Product> results = new ArrayList<>();
        String lowerQuery = query.toLowerCase();
        
        for (Product product : products.values()) {
            if (product.getName().toLowerCase().contains(lowerQuery) ||
                product.getDescription().toLowerCase().contains(lowerQuery) ||
                product.getCategory().toLowerCase().contains(lowerQuery)) {
                results.add(product);
            }
        }
        
        return results;
    }
} 