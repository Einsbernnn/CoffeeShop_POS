import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class POSLogin extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public POSLogin() {
        setTitle("POS Login");
        setSize(300, 180);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        usernameField = new JTextField();
        passwordField = new JPasswordField();

        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);

        JButton loginBtn = new JButton("Login");
        panel.add(new JLabel());
        panel.add(loginBtn);

        add(panel);

        loginBtn.addActionListener(e -> login());
    }

    private void login() {
        String user = usernameField.getText();
        String pass = new String(passwordField.getPassword());

        if (user.equals("admin") && pass.equals("1234")) {
            new POSMain(user);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid login.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(POSLogin::new);
    }
}