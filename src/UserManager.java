import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private static UserManager instance;
    private Map<String, User> users;
    private final Map<String, User> activeUsers = new HashMap<>();
    
    private UserManager() {
        users = new HashMap<>();
        initializeDefaultUsers();
    }
    
    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }
    
    private void initializeDefaultUsers() {
        users.put("admin", new User("admin", "1234", "Administrator", UserRole.ADMIN));
        users.put("cashier", new User("cashier", "5678", "Cashier", UserRole.CASHIER));
        users.put("manager", new User("manager", "9999", "Manager", UserRole.MANAGER));
        users.put("barista", new User("barista", "1111", "Barista", UserRole.BARISTA));
    }
    
    public User authenticateUser(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
    
    public boolean addUser(String username, String password, String displayName, UserRole role) {
        if (users.containsKey(username)) {
            return false; // User already exists
        }
        
        User newUser = new User(username, password, displayName, role);
        users.put(username, newUser);
        return true;
    }
    
    public boolean removeUser(String username) {
        if (username.equals("admin")) {
            return false; // Cannot remove admin
        }
        return users.remove(username) != null;
    }
    
    public boolean updateUserPassword(String username, String newPassword) {
        User user = users.get(username);
        if (user != null) {
            user.setPassword(newPassword);
            return true;
        }
        return false;
    }
    
    public User getUser(String username) {
        return users.get(username);
    }
    
    public Map<String, User> getAllUsers() {
        return new HashMap<>(users);
    }
    
    public boolean userExists(String username) {
        return users.containsKey(username);
    }
    
    public boolean isAdmin(String username) {
        User user = users.get(username);
        return user != null && user.getRole() == UserRole.ADMIN;
    }
    
    public boolean isManager(String username) {
        User user = users.get(username);
        return user != null && (user.getRole() == UserRole.MANAGER || user.getRole() == UserRole.ADMIN);
    }

    /**
     * Marks a user as logged in (present).
     */
    public void userLoggedIn(User user) {
        if (user != null) {
            activeUsers.put(user.getUsername(), user);
        }
    }

    /**
     * Marks a user as logged out (not present).
     */
    public void userLoggedOut(String username) {
        activeUsers.remove(username);
    }

    /**
     * Returns a copy of the currently active (present) users.
     */
    public Map<String, User> getActiveUsers() {
        return new HashMap<>(activeUsers);
    }
} 