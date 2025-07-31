import javax.swing.*;
import java.awt.*;

public class RoleDisplayPanel extends JPanel {
    private JLabel roleLabel;
    private User currentUser;
    
    public RoleDisplayPanel() {
        setLayout(new BorderLayout());
        setOpaque(false);
        setPreferredSize(new Dimension(300, 40));
        
        roleLabel = new JLabel("Not logged in");
        roleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        roleLabel.setForeground(Color.WHITE);
        roleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        roleLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        add(roleLabel, BorderLayout.EAST);
    }
    
    public void setCurrentUser(User user) {
        this.currentUser = user;
        updateDisplay();
    }
    
    public void clearUser() {
        this.currentUser = null;
        roleLabel.setText("Not logged in");
    }
    
    private void updateDisplay() {
        if (currentUser != null) {
            String displayText = "On Duty: " + currentUser.getRole().getDisplayName() + 
                               " - " + currentUser.getDisplayName();
            roleLabel.setText(displayText);
        } else {
            roleLabel.setText("Not logged in");
        }
    }
} 