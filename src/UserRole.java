public enum UserRole {
    ADMIN("Administrator"),
    MANAGER("Manager"),
    CASHIER("Cashier"),
    BARISTA("Barista");
    
    private final String displayName;
    
    UserRole(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
} 