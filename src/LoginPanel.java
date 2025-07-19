import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel statusLabel;
    private JButton loginBtn;
    private LoginListener loginListener;
    
    public interface LoginListener {
        void onLoginSuccess(User user);
        void onLoginFailure(String message);
    }
    
    public LoginPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245));
        initComponents();
    }
    
    private void initComponents() {
        // Header panel
        JPanel headerPanel = createHeaderPanel();
        
        // Form panel
        JPanel formPanel = createFormPanel();
        
        // Demo credentials panel
        JPanel demoPanel = createDemoPanel();
        
        add(headerPanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(demoPanel, BorderLayout.SOUTH);
    }
    
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(139, 69, 19)); // Coffee brown
        headerPanel.setPreferredSize(new Dimension(400, 80));
        
        JLabel titleLabel = new JLabel("☕ Coffee Shop POS");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(titleLabel);
        
        return headerPanel;
    }
    
    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        
        // Username field
        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(userLabel, gbc);
        
        usernameField = new JTextField(20);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1; gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(usernameField, gbc);
        
        // Password field
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        formPanel.add(passLabel, gbc);
        
        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1; gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(passwordField, gbc);
        
        // Status label
        statusLabel = new JLabel("Enter your credentials to login");
        statusLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        statusLabel.setForeground(Color.GRAY);
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(statusLabel, gbc);
        
        // Login button
        loginBtn = new JButton("Login");
        loginBtn.setFont(new Font("Arial", Font.BOLD, 14));
        loginBtn.setBackground(new Color(34, 139, 34)); // Green
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFocusPainted(false);
        loginBtn.setPreferredSize(new Dimension(120, 35));
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(loginBtn, gbc);
        
        setupEventListeners();
        
        return formPanel;
    }
    
    private JPanel createDemoPanel() {
        JPanel demoPanel = new JPanel();
        demoPanel.setBackground(new Color(240, 240, 240));
        demoPanel.setBorder(BorderFactory.createTitledBorder("Demo Credentials"));
        
        JTextArea demoText = new JTextArea(
            "Admin: admin/1234\n" +
            "Cashier: cashier/5678\n" +
            "Manager: manager/9999\n" +
            "Barista: barista/1111"
        );
        demoText.setEditable(false);
        demoText.setBackground(new Color(240, 240, 240));
        demoText.setFont(new Font("Monospaced", Font.PLAIN, 11));
        demoPanel.add(demoText);
        
        return demoPanel;
    }
    
    private void setupEventListeners() {
        loginBtn.addActionListener(e -> performLogin());
        
        // Enter key listener
        KeyAdapter enterListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    performLogin();
                }
            }
        };
        
        usernameField.addKeyListener(enterListener);
        passwordField.addKeyListener(enterListener);
        
        // Focus listeners for better UX
        usernameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                statusLabel.setText("Enter your username");
            }
        });
        
        passwordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                statusLabel.setText("Enter your password");
            }
        });
    }
    
    private void performLogin() {
        String user = usernameField.getText().trim();
        String pass = new String(passwordField.getPassword());

        if (user.isEmpty() || pass.isEmpty()) {
            setStatus("Please enter both username and password", Color.RED);
            return;
        }

        User authenticatedUser = UserManager.getInstance().authenticateUser(user, pass);
        if (authenticatedUser != null) {
            setStatus("Login successful! Opening POS...", new Color(34, 139, 34));
            loginBtn.setEnabled(false);
            
            if (loginListener != null) {
                loginListener.onLoginSuccess(authenticatedUser);
            }
        } else {
            setStatus("Invalid username or password", Color.RED);
            passwordField.setText("");
            passwordField.requestFocus();
        }
    }
    
    public void setStatus(String message, Color color) {
        statusLabel.setText(message);
        statusLabel.setForeground(color);
    }
    
    public void setLoginListener(LoginListener listener) {
        this.loginListener = listener;
    }
    
    public void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
        statusLabel.setText("Enter your credentials to login");
        statusLabel.setForeground(Color.GRAY);
        loginBtn.setEnabled(true);
    }
} 