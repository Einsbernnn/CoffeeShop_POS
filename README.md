# ☕ Coffee Shop POS System

A comprehensive Point of Sale (POS) system designed specifically for coffee shops and cafes. Built with Java Swing, this application provides a modern, user-friendly interface for managing sales, inventory, and customer transactions.

## 🚀 Features

### Core POS Features
- **Product Management**: Complete menu with categories (Coffee, Tea, Food, Desserts, Drinks)
- **Order Processing**: Add items to cart, customize orders, and process payments
- **Payment Methods**: Support for Cash, and Digital Wallet(To Be Added) and Also RFID System using arduino and RC522
- **Real-time Pricing**: Automatic price calculation with tax and discounts
- **Order History**: Track all transactions and sales data

### User Management
- **Multi-role System**: Admin, Manager, Cashier, and Barista roles
- **Secure Authentication**: Username/password login with RFID support
- **User Presence Tracking**: Monitor who's logged in and their shift times
- **Role-based Access**: Different permissions for different user types

### Inventory & Reports
- **Inventory Management**: Track product stock levels depends on items
- **Sales Reports**: Generate detailed sales reports by date (Month, Daily, Weekly, and Annualy)
- **Activity Logging**: Comprehensive audit trail of all system activities
- **Daily Analytics**: Track daily sales, orders, and performance metrics

### Advanced Features
- **Product Images**: Visual product display with custom images
- **RFID Login**: Secure card-based authentication
- **Customization Options**: Size, temperature, extras, and special requests
- **Clock Integration**: Real-time clock display on all screens
- **Responsive Design**: Adapts to different screen sizes

## 📋 Prerequisites

- **Java 8 or higher** (JDK 11+ recommended)
- **Git** (for cloning the repository)
- **At least 512MB RAM** available
- **Windows/macOS/Linux** compatible

## 🛠️ Installation & Setup

### Step 1: Clone the Repository
```bash
git clone <repository-url>
cd MyPOS
```

### Step 2: Compile the Application
```bash
# Navigate to src directory
cd src

# Compile all Java files
javac *.java
```

### Step 3: Generate Product Images (Optional)
```bash
# Generate placeholder images for all products
java GenerateProductImages
```

### Step 4: Run the Application
```bash
# From the src directory
java POSLogin

# Or from the project root
java -cp . src.POSLogin
```

## 👥 User Roles & Access

### Default Login Credentials

| Role | Username | Password | Access Level |
|------|----------|----------|--------------|
| Admin | admin | 1234 | Full system access |
| Manager | manager | 9999 | Sales, reports, inventory |
| Cashier | cashier | 5678 | Sales and basic reports |
| Barista | barista | 1111 | Product management |

### Role Permissions

**Admin**
- Full system access
- User management
- System configuration
- All reports and analytics

**Manager**
- Sales and transactions
- Inventory management
- Sales reports
- User presence monitoring

**Cashier**
- Process sales
- View basic reports
- Product lookup
- Customer service

**Barista**
- Product management
- Inventory updates
- Order preparation
- Basic sales

## 🎯 How to Use

### Starting the Application
1. Run `java POSLogin` from the src directory
2. Enter your username and password
3. Enter your full name when prompted
4. The main POS interface will open

### Processing a Sale
1. **Select Products**: Click on product buttons in the menu tabs
2. **Customize**: Choose size, temperature, and extras
3. **Add to Cart**: Products appear in the cart table
4. **Payment**: Select payment method and complete transaction
5. **Receipt**: System generates a sales receipt

### Managing Inventory
1. Navigate to the "Inventory" tab
2. View current stock levels
3. Update quantities as needed
4. Monitor low stock alerts

### Generating Reports
1. Go to the "Reports" tab
2. Select report type (Daily Sales, Product Performance, etc.)
3. Choose date range
4. Export or print reports

## 🖼️ Product Images

### Adding Custom Images
1. Place your images in `images/products/` directory
2. Use the naming convention: `product_name.jpg`
   - Example: `espresso.jpg`, `caramel_latte.jpg`
3. Images are automatically loaded and displayed

### Image Requirements
- **Format**: JPG, PNG, or other common formats
- **Size**: Recommended 200x200 pixels or larger
- **Naming**: Lowercase with underscores for spaces

### Default Placeholders
If no custom image is found, the system generates beautiful placeholder images with:
- Category-specific colors
- Relevant icons (☕ 🍵 🥐 🍰 🥤)
- Product names clearly displayed

## 📊 Reports & Analytics

### Available Reports
- **Daily Sales Report**: Complete daily transaction summary
- **Product Performance**: Best/worst selling items
- **User Activity**: Staff performance and login times
- **Inventory Status**: Stock levels and alerts
- **Payment Analysis**: Payment method breakdown

### Report Features
- Export to text files
- Date range selection
- Detailed transaction logs
- Performance metrics

## 🔧 Configuration

### Database Setup
The system uses a simple file-based database. To set up:

1. **Initialize Database**:
```sql
-- Run database.sql to create tables
-- Located in the project root
```

2. **Configure Connection**:
- Edit `DatabaseManager.java` for custom database settings
- Default uses SQLite for simplicity

### RFID Configuration
To enable RFID login:

1. **Install RFID Reader**: Connect USB RFID reader
2. **Configure Port**: Update port name in `RFIDReader.java`
   - Windows: `COM3`, `COM4`, etc.
   - macOS/Linux: `/dev/ttyUSB0`, `/dev/ttyACM0`
3. **Register Tags**: Use admin interface to register RFID cards

### Customization
- **Product Categories**: Modify `ProductManager.java`
- **Pricing**: Update product prices in the manager interface
- **Tax Rates**: Configure in `POSMain.java`
- **Receipt Format**: Customize in `Order.java`

## 🛡️ Security Features

### Authentication
- **Password Protection**: Encrypted password storage
- **Session Management**: Automatic logout on window close
- **Activity Logging**: All actions are logged with timestamps
- **RFID Security**: Secure card-based authentication

### Data Protection
- **Transaction Logs**: All sales are permanently recorded
- **User Audit Trail**: Track all user activities
- **Backup Recommendations**: Regular database backups

## 🐛 Troubleshooting

### Common Issues

**Application Won't Start**
```bash
# Check Java version
java -version

# Recompile all files
cd src && javac *.java

# Check for missing dependencies
java -cp . POSLogin
```

**Images Not Displaying**
1. Verify images exist in `images/products/`
2. Check file naming convention
3. Ensure file permissions are correct
4. Restart application after adding images

**RFID Not Working**
1. Check USB connection
2. Verify port configuration
3. Test with different RFID cards
4. Check system device manager

**Database Issues**
1. Verify database.sql was executed
2. Check file permissions
3. Ensure sufficient disk space
4. Restart application

### Performance Optimization
- **Memory**: Increase JVM heap size if needed
- **Images**: Optimize image sizes for faster loading
- **Database**: Regular cleanup of old logs
- **Updates**: Keep Java version current

## 📁 Project Structure

```
MyPOS/
├── src/                          # Source code
│   ├── *.java                   # Main application files
│   ├── model/                   # Data models
│   └── ui/                      # User interface components
├── images/                       # Product images
│   └── products/                # Product image files
├── database.sql                 # Database schema
├── activity_log.txt             # System activity log
├── sales_report_*.txt          # Generated reports
├── PRODUCT_IMAGES_README.md     # Image documentation
└── README.md                    # This file
```

## 🔄 Maintenance

### Regular Tasks
1. **Daily**: Check sales reports and activity logs
2. **Weekly**: Backup database and transaction logs
3. **Monthly**: Update product images and pricing
4. **Quarterly**: Review user permissions and access

### Backup Procedures
```bash
# Backup important files
cp activity_log.txt backup/
cp database.sql backup/
cp -r images/ backup/
```

### Updates
1. **Code Updates**: Pull latest changes from repository
2. **Recompile**: Run `javac *.java` in src directory
3. **Test**: Verify all features work correctly
4. **Deploy**: Replace old files with new versions

## 🤝 Contributing

### Development Setup
1. Fork the repository
2. Create feature branch
3. Make changes and test thoroughly
4. Submit pull request

### Code Standards
- Follow Java naming conventions
- Add comments for complex logic
- Test all new features
- Update documentation

## 📞 Support

### Getting Help
1. Check this README for common solutions
2. Review activity logs for error details
3. Test with different user accounts
4. Verify system requirements

### Contact Information
- **Issues**: Create GitHub issue with detailed description
- **Features**: Submit feature request with use case
- **Documentation**: Suggest improvements to this README

## 📄 Developed By Einsbernnn

This project is an opensource project created by Einsbernnn

## 🙏 Acknowledgments

- Java Swing for the user interface
- SQLite for data storage
- Open source community for inspiration
- Coffee shop owners for feedback and testing

---

**Version**: 1.0.0  
**Last Updated**: July 2024  
**Java Version**: 8+  
**Platform**: Cross-platform (Windows, macOS, Linux) 