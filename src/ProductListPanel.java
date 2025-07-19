import java.awt.*;
import javax.swing.*;

public class ProductListPanel extends JPanel {
    private POSMain mainPOS;
    private JTabbedPane categoryTabs;

    public ProductListPanel(POSMain posMainRef) {
        this.mainPOS = posMainRef;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Menu"));
        setBackground(Color.WHITE);

        createCategoryTabs();
        add(categoryTabs, BorderLayout.CENTER);
    }

    private void createCategoryTabs() {
        categoryTabs = new JTabbedPane();
        
        // Coffee Tab
        categoryTabs.addTab("☕ Coffee", createCoffeePanel());
        
        // Tea Tab
        categoryTabs.addTab("🍵 Tea", createTeaPanel());
        
        // Food Tab
        categoryTabs.addTab("🥐 Food", createFoodPanel());
        
        // Desserts Tab
        categoryTabs.addTab("🍰 Desserts", createDessertsPanel());
        
        // Drinks Tab
        categoryTabs.addTab("🥤 Drinks", createDrinksPanel());
    }

    private JPanel createCoffeePanel() {
        JPanel panel = new JPanel(new GridLayout(4, 3, 10, 10));
        panel.setBackground(Color.WHITE);

        String[][] coffees = {
                {"Espresso", "60", "Strong single shot"},
                {"Americano", "80", "Espresso + hot water"},
                {"Latte", "100", "Espresso + steamed milk"},
                {"Cappuccino", "100", "Equal parts espresso, milk, foam"},
                {"Mocha", "120", "Espresso + chocolate + milk"},
                {"Macchiato", "110", "Espresso with milk foam"},
                {"Flat White", "105", "Espresso + microfoam"},
                {"Affogato", "130", "Espresso + vanilla ice cream"},
                {"Cold Brew", "90", "12-hour cold extraction"},
                {"Iced Latte", "100", "Latte over ice"},
                {"Caramel Latte", "120", "Latte + caramel syrup"},
                {"Matcha Latte", "110", "Matcha + steamed milk"}
        };

        for (String[] coffee : coffees) {
            String name = coffee[0];
            String price = coffee[1];
            String description = coffee[2];

            JButton button = new JButton("<html><center><b>" + name + "</b><br>₱" + price + 
                    "<br><small>" + description + "</small></center></html>");
            styleButton(button, new Color(139, 69, 19)); // Brown color for coffee
            double priceVal = Double.parseDouble(price);

            button.addActionListener(e -> {
                mainPOS.addToCartFromList(name, priceVal);
            });

            panel.add(button);
        }

        return panel;
    }

    private JPanel createTeaPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 3, 10, 10));
        panel.setBackground(Color.WHITE);

        String[][] teas = {
                {"Green Tea", "50", "Traditional green tea"},
                {"Black Tea", "50", "Strong black tea"},
                {"Chai Latte", "90", "Spiced tea + milk"},
                {"Earl Grey", "60", "Bergamot black tea"},
                {"Chamomile", "50", "Calming herbal tea"},
                {"Peppermint", "50", "Refreshing mint tea"},
                {"Jasmine Tea", "60", "Fragrant jasmine green"},
                {"Oolong Tea", "70", "Semi-fermented tea"},
                {"Rooibos", "55", "South African red tea"},
                {"Lemon Tea", "55", "Black tea + lemon"},
                {"Honey Tea", "65", "Tea with honey"},
                {"Iced Tea", "60", "Cold brewed tea"}
        };

        for (String[] tea : teas) {
            String name = tea[0];
            String price = tea[1];
            String description = tea[2];

            JButton button = new JButton("<html><center><b>" + name + "</b><br>₱" + price + 
                    "<br><small>" + description + "</small></center></html>");
            styleButton(button, new Color(34, 139, 34)); // Green color for tea
            double priceVal = Double.parseDouble(price);

            button.addActionListener(e -> {
                mainPOS.addToCartFromList(name, priceVal);
            });

            panel.add(button);
        }

        return panel;
    }

    private JPanel createFoodPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 3, 10, 10));
        panel.setBackground(Color.WHITE);

        String[][] foods = {
                {"Croissant", "80", "Buttery French pastry"},
                {"Danish", "90", "Sweet filled pastry"},
                {"Muffin", "70", "Blueberry or chocolate"},
                {"Bagel", "60", "Toasted with cream cheese"},
                {"Sandwich", "120", "Ham & cheese or veggie"},
                {"Toast", "50", "Buttered or jam"},
                {"Cookie", "40", "Chocolate chip or oatmeal"},
                {"Brownie", "60", "Chocolate brownie"},
                {"Scone", "75", "Traditional English scone"},
                {"Panini", "130", "Grilled sandwich"},
                {"Quiche", "140", "Savory egg pie"},
                {"Salad", "150", "Fresh garden salad"}
        };

        for (String[] food : foods) {
            String name = food[0];
            String price = food[1];
            String description = food[2];

            JButton button = new JButton("<html><center><b>" + name + "</b><br>₱" + price + 
                    "<br><small>" + description + "</small></center></html>");
            styleButton(button, new Color(255, 215, 0)); // Gold color for food
            double priceVal = Double.parseDouble(price);

            button.addActionListener(e -> {
                mainPOS.addToCartFromList(name, priceVal);
            });

            panel.add(button);
        }

        return panel;
    }

    private JPanel createDessertsPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 3, 10, 10));
        panel.setBackground(Color.WHITE);

        String[][] desserts = {
                {"Cheesecake", "180", "New York style"},
                {"Tiramisu", "200", "Italian coffee dessert"},
                {"Chocolate Cake", "160", "Rich chocolate layer"},
                {"Apple Pie", "140", "Traditional apple pie"},
                {"Ice Cream", "120", "Vanilla, chocolate, strawberry"},
                {"Pudding", "100", "Chocolate or vanilla"},
                {"Cupcake", "80", "Vanilla or chocolate"},
                {"Donut", "70", "Glazed or filled"},
                {"Macaron", "90", "French almond cookie"},
                {"Eclair", "110", "Chocolate filled pastry"},
                {"Cannoli", "130", "Italian cream filled"},
                {"Fruit Tart", "150", "Fresh fruit tart"}
        };

        for (String[] dessert : desserts) {
            String name = dessert[0];
            String price = dessert[1];
            String description = dessert[2];

            JButton button = new JButton("<html><center><b>" + name + "</b><br>₱" + price + 
                    "<br><small>" + description + "</small></center></html>");
            styleButton(button, new Color(255, 182, 193)); // Pink color for desserts
            double priceVal = Double.parseDouble(price);

            button.addActionListener(e -> {
                mainPOS.addToCartFromList(name, priceVal);
            });

            panel.add(button);
        }

        return panel;
    }

    private JPanel createDrinksPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 3, 10, 10));
        panel.setBackground(Color.WHITE);

        String[][] drinks = {
                {"Hot Chocolate", "90", "Rich chocolate drink"},
                {"Smoothie", "120", "Fruit smoothie"},
                {"Juice", "80", "Fresh orange or apple"},
                {"Soda", "60", "Coke, Sprite, Fanta"},
                {"Water", "30", "Bottled water"},
                {"Milk", "50", "Cold milk"},
                {"Lemonade", "70", "Fresh lemonade"},
                {"Hot Tea", "50", "Various tea options"},
                {"Energy Drink", "100", "Red Bull or Monster"},
                {"Milkshake", "130", "Chocolate or vanilla"},
                {"Coconut Water", "90", "Fresh coconut water"},
                {"Sparkling Water", "70", "Carbonated water"}
        };

        for (String[] drink : drinks) {
            String name = drink[0];
            String price = drink[1];
            String description = drink[2];

            JButton button = new JButton("<html><center><b>" + name + "</b><br>₱" + price + 
                    "<br><small>" + description + "</small></center></html>");
            styleButton(button, new Color(135, 206, 235)); // Sky blue color for drinks
            double priceVal = Double.parseDouble(price);

            button.addActionListener(e -> {
                mainPOS.addToCartFromList(name, priceVal);
            });

            panel.add(button);
        }

        return panel;
    }

    private void styleButton(JButton button, Color backgroundColor) {
        button.setPreferredSize(new Dimension(140, 100));
        button.setFont(new Font("SansSerif", Font.BOLD, 12));
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        button.setOpaque(true);
        
        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(backgroundColor.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(backgroundColor);
            }
        });
    }
} 