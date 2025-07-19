import javax.swing.*;

public class POSLogin extends JFrame {

    public POSLogin() {
        setTitle("Coffee Shop POS - Login");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
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
        
        add(loginPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(POSLogin::new);
    }
}