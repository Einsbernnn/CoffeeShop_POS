import javax.swing.*;
import java.awt.*;

public class NamePromptDialog extends JDialog {
    private JTextField nameField;
    private String enteredName;
    private boolean confirmed = false;
    
    public NamePromptDialog(JFrame parent, String username, String role) {
        super(parent, "Welcome!", true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        
        initComponents(username, role);
        pack();
        setLocationRelativeTo(parent);
    }
    
    private void initComponents(String username, String role) {
        setLayout(new BorderLayout());
        
        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(139, 69, 19));
        headerPanel.setPreferredSize(new Dimension(400, 60));
        
        JLabel welcomeLabel = new JLabel("Welcome, " + username + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.setLayout(new BorderLayout());
        headerPanel.add(welcomeLabel, BorderLayout.CENTER);
        
        // Main content panel
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        JLabel instructionLabel = new JLabel("Please enter your full name:");
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2;
        contentPanel.add(instructionLabel, gbc);
        
        JLabel roleLabel = new JLabel("Role: " + role);
        roleLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        roleLabel.setForeground(Color.GRAY);
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 2;
        contentPanel.add(roleLabel, gbc);
        
        nameField = new JTextField(25);
        nameField.setFont(new Font("Arial", Font.PLAIN, 16));
        nameField.setPreferredSize(new Dimension(300, 35));
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(nameField, gbc);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        JButton confirmButton = new JButton("Confirm");
        confirmButton.setFont(new Font("Arial", Font.BOLD, 14));
        confirmButton.setBackground(new Color(34, 139, 34));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setPreferredSize(new Dimension(100, 35));
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Arial", Font.PLAIN, 14));
        cancelButton.setPreferredSize(new Dimension(100, 35));
        
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);
        
        // Add components to dialog
        add(headerPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Event listeners
        confirmButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (!name.isEmpty()) {
                enteredName = name;
                confirmed = true;
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Please enter your name", 
                    "Name Required", 
                    JOptionPane.WARNING_MESSAGE);
            }
        });
        
        cancelButton.addActionListener(e -> {
            confirmed = false;
            dispose();
        });
        
        // Enter key listener
        nameField.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    confirmButton.doClick();
                }
            }
        });
        
        // Focus on name field
        nameField.requestFocusInWindow();
    }
    
    public String getEnteredName() {
        return enteredName;
    }
    
    public boolean isConfirmed() {
        return confirmed;
    }
} 