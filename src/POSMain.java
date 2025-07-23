import java.awt.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class POSMain extends JFrame {
    private JTable cartTable;
    private DefaultTableModel tableModel;
    private JLabel totalLabel, userLabel, shiftLabel;
    private double totalAmount = 0.0;
    private String currentUser;
    private LocalDateTime shiftStart;
    private double dailySales = 0.0;
    private int dailyOrders = 0;
    
    // UI Components
    private JComboBox<String> paymentMethodCombo;
    private JComboBox<String> sizeCombo;
    private JCheckBox icedCheckBox, extraShotCheckBox, whippedCreamCheckBox;
    private JTextArea orderNotesArea;
    private JTabbedPane mainTabbedPane;
    
    // Managers
    private ProductManager productManager;
    private Inventory inventory;
    private List<Order> orderHistory;

    private DecimalFormat currencyFormat = new DecimalFormat("#,##0.00");
    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public POSMain(String username) {
        this.currentUser = username;
        this.shiftStart = LocalDateTime.now();
        this.productManager = ProductManager.getInstance();
        this.inventory = new Inventory();
        this.orderHistory = new ArrayList<>();
        
        setTitle("Coffee Shop POS - " + username + " | Shift: " + shiftStart.format(DateTimeFormatter.ofPattern("MM/dd HH:mm")));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        initComponents();
        setVisible(true);

        // Add window listener for logout
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                UserManager.getInstance().userLoggedOut(currentUser);
            }
        });
    }

    private void initComponents() {
        mainTabbedPane = new JTabbedPane();
        
        // Main POS Tab
        JPanel posPanel = createPOSPanel();
        mainTabbedPane.addTab("POS", posPanel);
        
        // Reports Tab
        JPanel reportsPanel = createReportsPanel();
        mainTabbedPane.addTab("Reports", reportsPanel);
        
        // Inventory Tab
        JPanel inventoryPanel = createInventoryPanel();
        mainTabbedPane.addTab("Inventory", inventoryPanel);
        
        // Add presence/role manager tab
        JPanel presencePanel = createPresencePanel();
        mainTabbedPane.addTab("Active Users", presencePanel);
        
        add(mainTabbedPane, BorderLayout.CENTER);
        
        // Bottom status bar
        JPanel statusBar = createStatusBar();
        add(statusBar, BorderLayout.SOUTH);
    }

    private JPanel createPOSPanel() {
        JPanel posPanel = new JPanel(new BorderLayout());
        
        // === Top Control Panel ===
        JPanel topPanel = new JPanel(new BorderLayout());
        
        // Left: Product Input
        JPanel inputPanel = new JPanel(new GridLayout(3, 3, 5, 5));
        JTextField productNameField = new JTextField();
        JTextField productPriceField = new JTextField();
        
        inputPanel.add(new JLabel("Product Name:"));
        inputPanel.add(productNameField);
        inputPanel.add(new JLabel());
        inputPanel.add(new JLabel("Price:"));
        inputPanel.add(productPriceField);
        
        JButton addButton = new JButton("Add to Cart");
        inputPanel.add(addButton);
        
        // Right: Customization Panel
        JPanel customizationPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        customizationPanel.setBorder(BorderFactory.createTitledBorder("Customization"));
        
        sizeCombo = new JComboBox<>(new String[]{"Small", "Medium", "Large"});
        icedCheckBox = new JCheckBox("Iced");
        extraShotCheckBox = new JCheckBox("Extra Shot (+₱20)");
        whippedCreamCheckBox = new JCheckBox("Whipped Cream (+₱15)");
        
        customizationPanel.add(new JLabel("Size:"));
        customizationPanel.add(sizeCombo);
        customizationPanel.add(icedCheckBox);
        customizationPanel.add(extraShotCheckBox);
        customizationPanel.add(whippedCreamCheckBox);
        customizationPanel.add(new JLabel("Notes:"));
        
        orderNotesArea = new JTextArea(2, 20);
        JScrollPane notesScroll = new JScrollPane(orderNotesArea);
        customizationPanel.add(notesScroll);
        
        topPanel.add(inputPanel, BorderLayout.WEST);
        topPanel.add(customizationPanel, BorderLayout.CENTER);
        
        // === Cart Table ===
        String[] columns = {"Product", "Customization", "Price", "Qty", "Total"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3; // Only quantity is editable
            }
        };
        cartTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(cartTable);
        
        // === Payment Panel ===
        JPanel paymentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        paymentMethodCombo = new JComboBox<>(new String[]{"Cash", "Card", "Digital Wallet"});
        JButton removeButton = new JButton("Remove Selected");
        JButton checkoutButton = new JButton("Checkout");
        JButton clearButton = new JButton("Clear Cart");
        
        paymentPanel.add(new JLabel("Payment:"));
        paymentPanel.add(paymentMethodCombo);
        paymentPanel.add(removeButton);
        paymentPanel.add(checkoutButton);
        paymentPanel.add(clearButton);
        
        // === Product List Panel ===
        ProductListPanel productListPanel = new ProductListPanel(this);
        productListPanel.setPreferredSize(new Dimension(700, getHeight()));
        
        // === Layout ===
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(topPanel, BorderLayout.NORTH);
        rightPanel.add(tableScrollPane, BorderLayout.CENTER);
        rightPanel.add(paymentPanel, BorderLayout.SOUTH);
        
        posPanel.add(productListPanel, BorderLayout.WEST);
        posPanel.add(rightPanel, BorderLayout.CENTER);
        
        // === Listeners ===
        addButton.addActionListener(e -> addCustomProduct(productNameField, productPriceField));
        removeButton.addActionListener(e -> removeSelectedItem());
        checkoutButton.addActionListener(e -> checkout());
        clearButton.addActionListener(e -> clearCart());
        
        return posPanel;
    }

    private JPanel createReportsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Sales Summary
        JPanel summaryPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        summaryPanel.setBorder(BorderFactory.createTitledBorder("Today's Summary"));
        
        JLabel totalSalesLabel = new JLabel("Total Sales: ₱" + currencyFormat.format(dailySales));
        JLabel totalOrdersLabel = new JLabel("Total Orders: " + dailyOrders);
        JLabel avgOrderLabel = new JLabel("Avg Order: ₱" + currencyFormat.format(dailyOrders > 0 ? dailySales / dailyOrders : 0));
        JLabel shiftTimeLabel = new JLabel("Shift Time: " + shiftStart.format(DateTimeFormatter.ofPattern("HH:mm")));
        
        summaryPanel.add(totalSalesLabel);
        summaryPanel.add(totalOrdersLabel);
        summaryPanel.add(avgOrderLabel);
        summaryPanel.add(shiftTimeLabel);
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton exportButton = new JButton("Export Report");
        JButton printButton = new JButton("Print Report");
        buttonPanel.add(exportButton);
        buttonPanel.add(printButton);
        
        panel.add(summaryPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        exportButton.addActionListener(e -> exportReport());
        printButton.addActionListener(e -> printReport());
        
        return panel;
    }

    private JPanel createInventoryPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Inventory Table
        String[] columns = {"Item", "Current Stock", "Min Stock", "Status"};
        DefaultTableModel inventoryModel = new DefaultTableModel(columns, 0);
        JTable inventoryTable = new JTable(inventoryModel);
        JScrollPane inventoryScroll = new JScrollPane(inventoryTable);
        
        // Populate inventory table
        for (Map.Entry<String, Integer> entry : inventory.getAllStock().entrySet()) {
            String item = entry.getKey();
            int stock = entry.getValue();
            String status = stock > 10 ? "In Stock" : stock > 0 ? "Low Stock" : "Out of Stock";
            inventoryModel.addRow(new Object[]{item, stock, 10, status});
        }
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addStockButton = new JButton("Add Stock");
        JButton lowStockButton = new JButton("Low Stock Alert");
        buttonPanel.add(addStockButton);
        buttonPanel.add(lowStockButton);
        
        panel.add(inventoryScroll, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        addStockButton.addActionListener(e -> addStock());
        lowStockButton.addActionListener(e -> showLowStockAlert());
        
        return panel;
    }

    private JPanel createPresencePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Active Users (Presence)"));

        String[] columns = {"Username", "Display Name", "Role"};
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(columns, 0);
        JTable table = new JTable(model);

        // Populate table with active users
        Map<String, User> activeUsers = UserManager.getInstance().getActiveUsers();
        for (User user : activeUsers.values()) {
            model.addRow(new Object[]{user.getUsername(), user.getDisplayName(), user.getRole().getDisplayName()});
        }

        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        // Refresh button
        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.addActionListener(e -> {
            model.setRowCount(0);
            Map<String, User> refreshed = UserManager.getInstance().getActiveUsers();
            for (User user : refreshed.values()) {
                model.addRow(new Object[]{user.getUsername(), user.getDisplayName(), user.getRole().getDisplayName()});
            }
        });
        panel.add(refreshBtn, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createStatusBar() {
        JPanel statusBar = new JPanel(new BorderLayout());
        statusBar.setBorder(BorderFactory.createEtchedBorder());
        
        totalLabel = new JLabel("Total: ₱0.00");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 18));
        
        userLabel = new JLabel("User: " + currentUser);
        shiftLabel = new JLabel("Shift: " + shiftStart.format(timeFormatter));
        
        statusBar.add(totalLabel, BorderLayout.WEST);
        statusBar.add(userLabel, BorderLayout.CENTER);
        statusBar.add(shiftLabel, BorderLayout.EAST);
        
        return statusBar;
    }

    public void addToCartFromList(String productName, double basePrice) {
        // Apply customization
        double finalPrice = basePrice;
        String customization = "";
        
        // Size pricing
        String size = (String) sizeCombo.getSelectedItem();
        if (size.equals("Medium")) finalPrice += 20;
        else if (size.equals("Large")) finalPrice += 40;
        
        // Add-ons
        if (extraShotCheckBox.isSelected()) {
            finalPrice += 20;
            customization += "Extra Shot ";
        }
        if (whippedCreamCheckBox.isSelected()) {
            finalPrice += 15;
            customization += "Whipped Cream ";
        }
        if (icedCheckBox.isSelected()) {
            customization += "Iced ";
        }
        
        customization += size;
        
        // Check if product already exists with same customization
        boolean found = false;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String existingName = tableModel.getValueAt(i, 0).toString();
            String existingCustom = tableModel.getValueAt(i, 1).toString();
            
            if (existingName.equals(productName) && existingCustom.equals(customization)) {
                // Found same product with same customization, increase quantity
                String qtyStr = tableModel.getValueAt(i, 3).toString();
                int qty = Integer.parseInt(qtyStr) + 1;
                double totalPrice = finalPrice * qty;
                
                tableModel.setValueAt(qty, i, 3);
                tableModel.setValueAt("₱" + currencyFormat.format(totalPrice), i, 4);
                found = true;
                break;
            }
        }

        if (!found) {
            tableModel.addRow(new Object[]{
                    productName,
                    customization,
                    "₱" + currencyFormat.format(finalPrice),
                    1,
                    "₱" + currencyFormat.format(finalPrice)
            });
        }

        totalAmount += finalPrice;
        updateTotal();
        
        // Clear customization options
        clearCustomization();
    }

    private void addCustomProduct(JTextField nameField, JTextField priceField) {
        String name = nameField.getText();
        try {
            double price = Double.parseDouble(priceField.getText());
            addToCartFromList(name, price);
            nameField.setText("");
            priceField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid price format.");
        }
    }

    private void clearCustomization() {
        sizeCombo.setSelectedIndex(0);
        icedCheckBox.setSelected(false);
        extraShotCheckBox.setSelected(false);
        whippedCreamCheckBox.setSelected(false);
        orderNotesArea.setText("");
    }

    private void clearCart() {
        tableModel.setRowCount(0);
        totalAmount = 0;
        updateTotal();
        clearCustomization();
    }

    private void updateTotal() {
        totalAmount = 0;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String totalStr = tableModel.getValueAt(i, 4).toString().replace("₱", "").replace(",", "");
            totalAmount += Double.parseDouble(totalStr);
        }
        totalLabel.setText("Total: ₱" + currencyFormat.format(totalAmount));
    }

    private void removeSelectedItem() {
        int selected = cartTable.getSelectedRow();
        if (selected >= 0) {
            String totalStr = tableModel.getValueAt(selected, 4).toString().replace("₱", "").replace(",", "");
            double itemTotal = Double.parseDouble(totalStr);
            totalAmount -= itemTotal;
            tableModel.removeRow(selected);
            updateTotal();
        } else {
            JOptionPane.showMessageDialog(this, "Please select an item to remove.");
        }
    }

    private void checkout() {
        if (tableModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Cart is empty.");
            return;
        }

        String paymentMethod = (String) paymentMethodCombo.getSelectedItem();
        
        // Create order
        Order order = new Order();
        order.setOrderNumber(generateOrderNumber());
        order.setCustomerName("Walk-in Customer");
        order.setOrderTime(LocalDateTime.now());
        order.setPaymentMethod(paymentMethod);
        order.setTotal(totalAmount);
        order.setCashierName(currentUser);
        
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            OrderItem item = new OrderItem();
            item.setProductName(tableModel.getValueAt(i, 0).toString());
            item.setCustomization(tableModel.getValueAt(i, 1).toString());
            item.setQuantity(Integer.parseInt(tableModel.getValueAt(i, 3).toString()));
            item.setUnitPrice(Double.parseDouble(tableModel.getValueAt(i, 2).toString().replace("₱", "").replace(",", "")));
            order.addItem(item);
        }
        
        orderHistory.add(order);
        
        // Log sale
        ActivityLogger.log(currentUser, "SALE", "Order#" + order.getOrderNumber() + ", Total: ₱" + order.getTotal());
        
        // Update daily stats
        dailySales += totalAmount;
        dailyOrders++;
        
        // Generate receipt
        String receipt = generateReceipt(order);
        
        // Show receipt dialog
        JTextArea receiptArea = new JTextArea(receipt);
        receiptArea.setEditable(false);
        receiptArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        JOptionPane.showMessageDialog(this, new JScrollPane(receiptArea), 
                "Receipt - Order #" + order.getOrderNumber(), 
                JOptionPane.INFORMATION_MESSAGE);

        // Reset cart
        clearCart();
    }

    private String generateOrderNumber() {
        return "ORD" + System.currentTimeMillis() % 10000;
    }

    private String generateReceipt(Order order) {
        StringBuilder receipt = new StringBuilder();
        receipt.append("=".repeat(40)).append("\n");
        receipt.append("           COFFEE SHOP POS\n");
        receipt.append("=".repeat(40)).append("\n");
        receipt.append("Order #: ").append(order.getOrderNumber()).append("\n");
        receipt.append("Date: ").append(order.getOrderTime().format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm"))).append("\n");
        receipt.append("Cashier: ").append(order.getCashierName()).append("\n");
        receipt.append("-".repeat(40)).append("\n");
        
        for (OrderItem item : order.getItems()) {
            receipt.append(String.format("%-20s %2dx ₱%8.2f\n", 
                item.getProductName(), item.getQuantity(), item.getUnitPrice()));
            if (!item.getCustomization().isEmpty()) {
                receipt.append(String.format("    %s\n", item.getCustomization()));
            }
        }
        
        receipt.append("-".repeat(40)).append("\n");
        receipt.append(String.format("TOTAL: %30s\n", "₱" + currencyFormat.format(order.getTotal())));
        receipt.append("Payment: ").append(order.getPaymentMethod()).append("\n");
        receipt.append("=".repeat(40)).append("\n");
        receipt.append("        Thank you for your order!\n");
        receipt.append("=".repeat(40)).append("\n");
        
        return receipt.toString();
    }

    private void exportReport() {
        try {
            String filename = "sales_report_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmm")) + ".txt";
            java.io.PrintWriter writer = new java.io.PrintWriter(new java.io.FileWriter(filename));
            writer.println("Coffee Shop Sales Report");
            writer.println("Date: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
            writer.println("Cashier: " + currentUser);
            writer.println("Total Sales: ₱" + currencyFormat.format(dailySales));
            writer.println("Total Orders: " + dailyOrders);
            writer.close();
            JOptionPane.showMessageDialog(this, "Report exported to " + filename);
        } catch (java.io.IOException e) {
            JOptionPane.showMessageDialog(this, "Error exporting report: " + e.getMessage());
        }
    }

    private void printReport() {
        JOptionPane.showMessageDialog(this, "Printing report...\n(Simulated printing)");
    }

    private void addStock() {
        String item = JOptionPane.showInputDialog(this, "Enter item name:");
        if (item != null && !item.trim().isEmpty()) {
            String quantityStr = JOptionPane.showInputDialog(this, "Enter quantity to add:");
            try {
                int quantity = Integer.parseInt(quantityStr);
                inventory.addStock(item, quantity);
                JOptionPane.showMessageDialog(this, "Added " + quantity + " units of " + item);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid quantity");
            }
        }
    }

    private void showLowStockAlert() {
        StringBuilder alert = new StringBuilder("Low Stock Items:\n\n");
        for (Map.Entry<String, Integer> entry : inventory.getLowStockItems().entrySet()) {
            alert.append(entry.getKey()).append(": ").append(entry.getValue()).append(" units\n");
        }
        JOptionPane.showMessageDialog(this, alert.toString(), "Low Stock Alert", JOptionPane.WARNING_MESSAGE);
    }

    // Inner classes for order management
    public static class Order {
        private String orderNumber;
        private String customerName;
        private LocalDateTime orderTime;
        private String paymentMethod;
        private double total;
        private List<OrderItem> items = new ArrayList<>();
        private String cashierName;

        // Getters and setters
        public String getOrderNumber() { return orderNumber; }
        public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }
        public String getCustomerName() { return customerName; }
        public void setCustomerName(String customerName) { this.customerName = customerName; }
        public LocalDateTime getOrderTime() { return orderTime; }
        public void setOrderTime(LocalDateTime orderTime) { this.orderTime = orderTime; }
        public String getPaymentMethod() { return paymentMethod; }
        public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
        public double getTotal() { return total; }
        public void setTotal(double total) { this.total = total; }
        public List<OrderItem> getItems() { return items; }
        public void setItems(List<OrderItem> items) { this.items = items; }
        public String getCashierName() { return cashierName; }
        public void setCashierName(String cashierName) { this.cashierName = cashierName; }

        public void addItem(OrderItem item) {
            items.add(item);
        }
    }

    public static class OrderItem {
        private String productName;
        private String customization;
        private int quantity;
        private double unitPrice;

        // Getters and setters
        public String getProductName() { return productName; }
        public void setProductName(String productName) { this.productName = productName; }
        public String getCustomization() { return customization; }
        public void setCustomization(String customization) { this.customization = customization; }
        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }
        public double getUnitPrice() { return unitPrice; }
        public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }
    }
}