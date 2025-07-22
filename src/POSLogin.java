import java.awt.*;
import javax.swing.*;

public class POSLogin extends JFrame {

    public POSLogin() {
        setTitle("Coffee Shop POS - Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245, 245, 245));
        
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        // Header bar similar to POSMain
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(139, 69, 19)); // Coffee brown
        headerPanel.setPreferredSize(new Dimension(400, 80));
        JLabel titleLabel = new JLabel("☕ Coffee Shop POS");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.setLayout(new BorderLayout());
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        add(headerPanel, BorderLayout.NORTH);

        // Center login panel
        LoginPanel loginPanel = new LoginPanel();
        loginPanel.setLoginListener(new LoginPanel.LoginListener() {
            @Override
            public void onLoginSuccess(User user) {
                // Simulate loading
                javax.swing.Timer timer = new javax.swing.Timer(1000, e -> {
                    new POSMain(user.getUsername());
                    dispose();
                });
                timer.setRepeats(false);
                timer.start();
            }
            
            @Override
            public void onLoginFailure(String message) {
                // Handle login failure if needed
            }
        });
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        centerPanel.add(loginPanel);
        add(centerPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(POSLogin::new);
    }
}