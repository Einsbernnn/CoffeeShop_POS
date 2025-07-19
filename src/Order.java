import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private String orderNumber;
    private String customerName;
    private LocalDateTime orderTime;
    private String paymentMethod;
    private double total;
    private List<OrderItem> items;
    private String cashierName;
    
    public Order() {
        this.items = new ArrayList<>();
        this.orderTime = LocalDateTime.now();
    }
    
    public Order(String orderNumber, String customerName, String paymentMethod, String cashierName) {
        this();
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.paymentMethod = paymentMethod;
        this.cashierName = cashierName;
    }
    
    // Getters
    public String getOrderNumber() { return orderNumber; }
    public String getCustomerName() { return customerName; }
    public LocalDateTime getOrderTime() { return orderTime; }
    public String getPaymentMethod() { return paymentMethod; }
    public double getTotal() { return total; }
    public List<OrderItem> getItems() { return items; }
    public String getCashierName() { return cashierName; }
    
    // Setters
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public void setOrderTime(LocalDateTime orderTime) { this.orderTime = orderTime; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public void setTotal(double total) { this.total = total; }
    public void setItems(List<OrderItem> items) { this.items = items; }
    public void setCashierName(String cashierName) { this.cashierName = cashierName; }
    
    // Business methods
    public void addItem(OrderItem item) {
        items.add(item);
        calculateTotal();
    }
    
    public void removeItem(OrderItem item) {
        items.remove(item);
        calculateTotal();
    }
    
    private void calculateTotal() {
        total = items.stream()
                .mapToDouble(item -> item.getUnitPrice() * item.getQuantity())
                .sum();
    }
    
    public int getItemCount() {
        return items.size();
    }
} 