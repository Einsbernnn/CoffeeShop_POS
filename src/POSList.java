import javax.swing.*;
import java.awt.*;

public class POSList extends JPanel {

    private POSMain mainPOS;

    public POSList(POSMain posMainRef) {
        this.mainPOS = posMainRef;

        setLayout(new GridLayout(4, 3, 10, 10));  // 4 rows, 3 columns
        setBorder(BorderFactory.createTitledBorder("Coffee Menu"));
        setBackground(Color.WHITE);

        String[][] coffees = {
                {"Espresso", "60"},
                {"Americano", "80"},
                {"Latte", "100"},
                {"Cappuccino", "100"},
                {"Mocha", "120"},
                {"Macchiato", "110"},
                {"Flat White", "105"},
                {"Affogato", "130"},
                {"Cold Brew", "90"},
                {"Iced Latte", "100"},
                {"Caramel Latte", "120"},
                {"Matcha Latte", "110"}
        };

        for (String[] coffee : coffees) {
            String name = coffee[0];
            String price = coffee[1];

            JButton button = new JButton("<html><center>" + name + "<br><b>₱" + price + "</b></center></html>");
            styleButton(button);
            double priceVal = Double.parseDouble(price);

            button.addActionListener(e -> {
                mainPOS.addToCartFromList(name, priceVal);
            });

            add(button);
        }
    }

    private void styleButton(JButton button) {
        button.setPreferredSize(new Dimension(120, 80));
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setBackground(new Color(230, 230, 250)); // Light tile color
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
    }
}