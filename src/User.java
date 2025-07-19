public class User {
    private String username;
    private String password;
    private String displayName;
    private UserRole role;
    
    public User(String username, String password, String displayName, UserRole role) {
        this.username = username;
        this.password = password;
        this.displayName = displayName;
        this.role = role;
    }
    
    // Getters
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getDisplayName() { return displayName; }
    public UserRole getRole() { return role; }
    
    // Setters
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }
    public void setRole(UserRole role) { this.role = role; }
    
    @Override
    public String toString() {
        return displayName + " (" + role.getDisplayName() + ")";
    }
} 