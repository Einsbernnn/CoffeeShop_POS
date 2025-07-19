# Coffee Shop POS System

A comprehensive Point of Sale (POS) system designed specifically for coffee shops, built with Java Swing and following Object-Oriented Programming principles.

## Features

### 🛍️ **Core POS Features**
- **Product Management**: Organized by categories (Coffee, Tea, Food, Desserts, Drinks)
- **Order Customization**: Size options, add-ons (extra shot, whipped cream), temperature preferences
- **Payment Methods**: Cash, Card, Digital Wallet support
- **Receipt Generation**: Professional formatted receipts with order details
- **Cart Management**: Add, remove, and modify items in real-time

### 👥 **User Management**
- **Multi-Role System**: Admin, Manager, Cashier, Barista roles
- **Secure Authentication**: Password-protected login system
- **User Session Tracking**: Shift management and user activity logging

### 📊 **Reporting & Analytics**
- **Sales Reports**: Daily sales tracking and analytics
- **Order History**: Complete order tracking and management
- **Export Functionality**: Generate and export sales reports
- **Real-time Statistics**: Live sales data and performance metrics

### 📦 **Inventory Management**
- **Stock Tracking**: Real-time inventory levels
- **Low Stock Alerts**: Automatic notifications for reordering
- **Inventory Reports**: Stock level monitoring and management
- **Add Stock Functionality**: Easy stock updates and management

### 🎨 **User Interface**
- **Modern Design**: Clean, intuitive interface with coffee shop theme
- **Responsive Layout**: Optimized for different screen sizes
- **Category Tabs**: Organized product display with visual icons
- **Color-coded Categories**: Easy visual identification of product types

## System Architecture

### **Model Classes**
- `User.java` - User data and role management
- `UserRole.java` - User role enumeration
- `Product.java` - Product information and pricing
- `Order.java` - Order management and processing
- `OrderItem.java` - Individual items within orders
- `Inventory.java` - Inventory tracking and management

### **Manager Classes**
- `UserManager.java` - User authentication and management (Singleton)
- `ProductManager.java` - Product catalog management (Singleton)

### **UI Components**
- `POSLogin.java` - Main login window
- `LoginPanel.java` - Login interface component
- `POSMain.java` - Main POS application window
- `ProductListPanel.java` - Product display with categories

## Getting Started

### **Prerequisites**
- Java 8 or higher
- Any Java IDE (IntelliJ IDEA, Eclipse, NetBeans)

### **Installation**
1. Clone or download the project
2. Open the project in your Java IDE
3. Compile and run `POSLogin.java`

### **Demo Credentials**
- **Admin**: admin/1234
- **Cashier**: cashier/5678
- **Manager**: manager/9999
- **Barista**: barista/1111

## Usage Guide

### **Login System**
1. Launch the application
2. Enter username and password
3. Select your role and access level

### **Processing Orders**
1. Select products from category tabs
2. Customize orders (size, add-ons, temperature)
3. Add to cart
4. Review and checkout
5. Generate receipt

### **Inventory Management**
1. Navigate to Inventory tab
2. View current stock levels
3. Add stock as needed
4. Monitor low stock alerts

### **Reports**
1. Access Reports tab
2. View daily sales summary
3. Export reports as needed
4. Monitor performance metrics

## Key Features Explained

### **Order Customization**
- **Size Options**: Small, Medium, Large with price adjustments
- **Add-ons**: Extra shot (+₱20), Whipped cream (+₱15)
- **Temperature**: Hot or Iced options
- **Special Instructions**: Notes field for custom requests

### **Payment Processing**
- **Multiple Payment Methods**: Cash, Card, Digital Wallet
- **Receipt Generation**: Professional formatted receipts
- **Order Tracking**: Unique order numbers and timestamps

### **Inventory System**
- **Real-time Tracking**: Live stock level monitoring
- **Automatic Alerts**: Low stock notifications
- **Easy Management**: Simple stock addition interface

### **User Roles**
- **Admin**: Full system access and management
- **Manager**: Sales reports and inventory management
- **Cashier**: Order processing and basic operations
- **Barista**: Product preparation and order management

## Technical Details

### **Design Patterns Used**
- **Singleton Pattern**: UserManager, ProductManager
- **MVC Pattern**: Separation of Model, View, Controller
- **Observer Pattern**: UI updates based on data changes

### **Data Management**
- **In-Memory Storage**: For demonstration purposes
- **Extensible Architecture**: Easy to integrate with databases
- **Modular Design**: Easy to add new features

### **UI/UX Features**
- **Responsive Design**: Adapts to different screen sizes
- **Intuitive Navigation**: Tab-based interface
- **Visual Feedback**: Hover effects and status indicators
- **Professional Styling**: Coffee shop themed colors and layout

## Future Enhancements

### **Planned Features**
- **Database Integration**: Persistent data storage
- **Customer Loyalty System**: Points and rewards
- **Advanced Reporting**: Detailed analytics and insights
- **Printing Support**: Direct receipt printing
- **Mobile Integration**: Tablet and mobile support
- **Payment Gateway**: Real payment processing
- **Inventory Alerts**: Email notifications for low stock

### **Technical Improvements**
- **Database Backend**: MySQL/PostgreSQL integration
- **REST API**: Web service integration
- **Cloud Storage**: Remote data synchronization
- **Multi-language Support**: Internationalization
- **Advanced Security**: Encryption and audit trails

## Contributing

This project is designed as a learning tool and demonstration of OOP principles. Feel free to:

1. **Fork the repository**
2. **Add new features**
3. **Improve existing functionality**
4. **Report bugs or issues**
5. **Suggest enhancements**

## License

This project is open source and available under the MIT License.

## Support

For questions, issues, or feature requests, please create an issue in the repository.

---

**Built with ❤️ for Coffee Shop Management** 