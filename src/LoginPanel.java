import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel statusLabel;
    private JButton loginBtn;
    private JButton rfidLoginBtn;
    private LoginListener loginListener;
    private RFIDReader rfidReader;
    
    public interface LoginListener {
        void onLoginSuccess(User user);
        void onLoginFailure(String message);
    }
    
    public LoginPanel() {
        setOpaque(false);
        setLayout(new GridBagLayout());
        initComponents();
    }
    
    private void initComponents() {
        JPanel cardPanel = new JPanel(new BorderLayout());
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(32, 32, 32, 32),
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
                BorderFactory.createEmptyBorder(32, 32, 32, 32)
            )
        ));
        cardPanel.setPreferredSize(new Dimension(480, 420));
        cardPanel.setMaximumSize(new Dimension(520, 480));
        cardPanel.setMinimumSize(new Dimension(400, 350));
        cardPanel.setOpaque(true);
        cardPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        cardPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));

        // Clock panel (upper left)
        JPanel clockRow = new JPanel(new BorderLayout());
        clockRow.setOpaque(false);
        clockRow.add(new ClockPanel(), BorderLayout.WEST);
        clockRow.setMaximumSize(new Dimension(480, 28));
        cardPanel.add(clockRow);

        // Header panel
        JPanel headerPanel = createHeaderPanel();
        headerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        cardPanel.add(headerPanel);

        // Form panel
        JPanel formPanel = createFormPanel();
        formPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        cardPanel.add(Box.createVerticalStrut(16));
        cardPanel.add(formPanel);

        // Demo credentials panel
        JPanel demoPanel = createDemoPanel();
        demoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        cardPanel.add(Box.createVerticalStrut(16));
        cardPanel.add(demoPanel);

        // Drop shadow effect
        setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0));
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 1, 8, 8, new Color(220, 220, 220, 180)),
            cardPanel.getBorder()
        ));

        add(cardPanel);
    }
    
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(139, 69, 19)); // Coffee brown
        headerPanel.setPreferredSize(new Dimension(400, 80));
        headerPanel.setMaximumSize(new Dimension(480, 80));
        
        JLabel titleLabel = new JLabel("☕ Coffee Shop Point of Sale");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.setLayout(new BorderLayout());
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        return headerPanel;
    }
    
    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 16, 10, 16);
        
        // Username field
        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(userLabel, gbc);
        
        usernameField = new JTextField(22);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 18));
        usernameField.setPreferredSize(new Dimension(220, 36));
        gbc.gridx = 1; gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(usernameField, gbc);
        
        // Password field
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        formPanel.add(passLabel, gbc);
        
        passwordField = new JPasswordField(22);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 18));
        passwordField.setPreferredSize(new Dimension(220, 36));
        gbc.gridx = 1; gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(passwordField, gbc);
        
        // Status label
        statusLabel = new JLabel("Enter your credentials to login");
        statusLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        statusLabel.setForeground(Color.GRAY);
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(statusLabel, gbc);
        
        // Login button
        loginBtn = new JButton("Login");
        loginBtn.setFont(new Font("Arial", Font.BOLD, 18));
        loginBtn.setBackground(new Color(34, 139, 34)); // Green
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFocusPainted(false);
        loginBtn.setPreferredSize(new Dimension(180, 44));
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(loginBtn, gbc);

        // RFID login button
        rfidLoginBtn = new JButton("RFID Login");
        rfidLoginBtn.setFont(new Font("Arial", Font.BOLD, 16));
        rfidLoginBtn.setBackground(new Color(70, 130, 180)); // Steel blue
        rfidLoginBtn.setForeground(Color.WHITE);
        rfidLoginBtn.setFocusPainted(false);
        rfidLoginBtn.setPreferredSize(new Dimension(180, 36));
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(rfidLoginBtn, gbc);
        
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
        rfidLoginBtn.addActionListener(e -> performRFIDLogin());
        
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
            ActivityLogger.log(user, "LOGIN_FAILED", "Missing username or password");
            return;
        }

        UserManager userManager = UserManager.getInstance();
        User authenticatedUser = userManager.authenticateUser(user, pass);
        if (authenticatedUser != null) {
            // Prompt for user's name
            NamePromptDialog nameDialog = new NamePromptDialog(
                (JFrame) SwingUtilities.getWindowAncestor(this),
                authenticatedUser.getUsername(),
                authenticatedUser.getRole().getDisplayName()
            );
            
            nameDialog.setVisible(true);
            
            if (nameDialog.isConfirmed()) {
                String enteredName = nameDialog.getEnteredName();
                authenticatedUser.setDisplayName(enteredName);
                
                userManager.userLoggedIn(authenticatedUser); // Track presence
                setStatus("Login successful! Opening POS...", new Color(34, 139, 34));
                loginBtn.setEnabled(false);
                ActivityLogger.log(user, "LOGIN_SUCCESS");
                if (loginListener != null) {
                    loginListener.onLoginSuccess(authenticatedUser);
                }
            } else {
                setStatus("Login cancelled", Color.ORANGE);
                loginBtn.setEnabled(true);
            }
        } else {
            setStatus("Invalid username or password", Color.RED);
            ActivityLogger.log(user, "LOGIN_FAILED", "Invalid credentials");
            if (loginListener != null) {
                loginListener.onLoginFailure("Invalid username or password");
            }
            passwordField.setText("");
            passwordField.requestFocus();
        }
    }

    private void performRFIDLogin() {
        setStatus("Waiting for RFID tag...", Color.BLUE);
        rfidLoginBtn.setEnabled(false);
        // Example port name: "/dev/ttyUSB0" or "COM3". User should configure this.
        String portName = "COM3"; // TODO: Make configurable
        rfidReader = new RFIDReader(portName, new RFIDReader.RFIDListener() {
            @Override
            public void onTagScanned(String tagId) {
                SwingUtilities.invokeLater(() -> {
                    setStatus("RFID tag detected: " + tagId, new Color(34, 139, 34));
                    UserManager userManager = UserManager.getInstance();
                    User user = userManager.authenticateRFID(tagId);
                    if (user != null) {
                        // Prompt for user's name
                        NamePromptDialog nameDialog = new NamePromptDialog(
                            (JFrame) SwingUtilities.getWindowAncestor(LoginPanel.this),
                            user.getUsername(),
                            user.getRole().getDisplayName()
                        );
                        
                        nameDialog.setVisible(true);
                        
                        if (nameDialog.isConfirmed()) {
                            String enteredName = nameDialog.getEnteredName();
                            user.setDisplayName(enteredName);
                            
                            userManager.userLoggedIn(user);
                            ActivityLogger.log(user.getUsername(), "LOGIN_SUCCESS", "RFID");
                            if (loginListener != null) loginListener.onLoginSuccess(user);
                        } else {
                            setStatus("Login cancelled", Color.ORANGE);
                        }
                    } else {
                        setStatus("Unknown RFID tag", Color.RED);
                        ActivityLogger.log("RFID", "LOGIN_FAILED", "Unknown tag: " + tagId);
                        if (loginListener != null) loginListener.onLoginFailure("Unknown RFID tag");
                    }
                    rfidLoginBtn.setEnabled(true);
                    if (rfidReader != null) rfidReader.close();
                });
            }
            @Override
            public void onError(String message) {
                SwingUtilities.invokeLater(() -> {
                    setStatus("RFID Error: " + message, Color.RED);
                    rfidLoginBtn.setEnabled(true);
                    if (rfidReader != null) rfidReader.close();
                });
            }
        });
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