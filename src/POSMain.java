import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.DecimalFormat;

public class POSMain extends JFrame {

    private JTable cartTable;
    private DefaultTableModel tableModel;
    private JTextField productNameField, productPriceField;
    private JLabel totalLabel, userLabel;
    private double totalAmount = 0.0;
    private String currentUser;

    private DecimalFormat currencyFormat = new DecimalFormat("#,##0.00");

    public POSMain(String username) {
        this.currentUser = username;
        setTitle("Java Swing POS - Logged in as " + username);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Fullscreen
        setUndecorated(false); // Optional: remove window borders
        setLayout(new BorderLayout());

        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        // === Top Input Panel ===
        JPanel inputPanel = new JPanel(new GridLayout(2, 3, 5, 5));
        productNameField = new JTextField();
        productPriceField = new JTextField();

        inputPanel.add(new JLabel("Product Name:"));
        inputPanel.add(productNameField);
        inputPanel.add(new JLabel());
        inputPanel.add(new JLabel("Price:"));
        inputPanel.add(productPriceField);

        JButton addButton = new JButton("Add to Cart");
        inputPanel.add(addButton);

        // === Cart Table ===
        String[] columns = {"Product", "Price", "Qty"};
        tableModel = new DefaultTableModel(columns, 0);
        cartTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(cartTable);

        // === Buttons ===
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton removeButton = new JButton("Remove Selected");
        JButton checkoutButton = new JButton("Checkout");
        buttonPanel.add(removeButton);
        buttonPanel.add(checkoutButton);

        // === Bottom Info ===
        JPanel bottomPanel = new JPanel(new BorderLayout());
        totalLabel = new JLabel("Total: ₱0.00");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 22));
        userLabel = new JLabel("Logged in as: " + currentUser);
        bottomPanel.add(totalLabel, BorderLayout.WEST);
        bottomPanel.add(userLabel, BorderLayout.EAST);

        // === POSList Panel (left-side, big tiles) ===
        POSList posListPanel = new POSList(this);
        posListPanel.setPreferredSize(new Dimension(600, getHeight()));  // Wider than input

        // === Add to Frame ===
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(inputPanel, BorderLayout.NORTH);
        rightPanel.add(tableScrollPane, BorderLayout.CENTER);
        rightPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(posListPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // === Listeners ===
        addButton.addActionListener(e -> {
            String name = productNameField.getText();
            try {
                double price = Double.parseDouble(productPriceField.getText());
                addToCartFromList(name, price);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid price format.");
            }
        });

        removeButton.addActionListener(e -> removeSelectedItem());
        checkoutButton.addActionListener(e -> checkout());
    }

    public void addToCartFromList(String productName, double price) {
        boolean found = false;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String existingName = tableModel.getValueAt(i, 0).toString();
            if (existingName.startsWith(productName)) {
                // Found same product, increase quantity
                String qtyStr = tableModel.getValueAt(i, 2).toString();
                int qty = Integer.parseInt(qtyStr) + 1;
                tableModel.setValueAt(productName + " x" + qty, i, 0);
                tableModel.setValueAt("₱" + currencyFormat.format(price * qty), i, 1);
                tableModel.setValueAt(qty, i, 2);
                found = true;
                break;
            }
        }

        if (!found) {
            tableModel.addRow(new Object[]{
                    productName,
                    "₱" + currencyFormat.format(price),
                    1
            });
        }

        totalAmount += price;
        updateTotal();
    }

    private void updateTotal() {
        totalLabel.setText("Total: ₱" + currencyFormat.format(totalAmount));
    }

    private void removeSelectedItem() {
        int selected = cartTable.getSelectedRow();
        if (selected >= 0) {
            String qtyStr = tableModel.getValueAt(selected, 2).toString();
            int qty = Integer.parseInt(qtyStr);
            String priceStr = tableModel.getValueAt(selected, 1).toString().replace("₱", "").replace(",", "");
            double price = Double.parseDouble(priceStr);
            totalAmount -= price;
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

        StringBuilder receipt = new StringBuilder("=== RECEIPT ===\n\n");
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            receipt.append(tableModel.getValueAt(i, 0))
                    .append(" - ")
                    .append(tableModel.getValueAt(i, 1))
                    .append("\n");
        }
        receipt.append("\nTOTAL: ₱").append(currencyFormat.format(totalAmount));
        receipt.append("\n\nThank you!");

        JOptionPane.showMessageDialog(this, receipt.toString(), "Receipt", JOptionPane.INFORMATION_MESSAGE);

        // Reset cart
        tableModel.setRowCount(0);
        totalAmount = 0;
        updateTotal();
    }
}