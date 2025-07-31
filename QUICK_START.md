# 🚀 Quick Start Guide

## ⚡ Get Started in 5 Minutes

### 1. Setup (One-time)
```bash
# Option A: Automated setup
./setup.sh                    # macOS/Linux
setup.bat                     # Windows

# Option B: Manual setup
cd src && javac *.java
java GenerateProductImages
```

### 2. Run the Application
```bash
cd src
java POSLogin
```

### 3. Login
| Role | Username | Password |
|------|----------|----------|
| Admin | admin | 1234 |
| Cashier | cashier | 5678 |
| Manager | manager | 9999 |
| Barista | barista | 1111 |

### 4. Start Selling!
1. Click product buttons to add to cart
2. Customize size, temperature, extras
3. Select payment method
4. Complete transaction

## 🎯 Essential Features

### **Sales Processing**
- Click products → Add to cart → Payment → Done!

### **Product Images**
- Images automatically load from `images/products/`
- Replace placeholders with your own photos
- Naming: `espresso.jpg`, `caramel_latte.jpg`

### **Reports**
- Go to "Reports" tab
- Select date range
- Export sales data

### **User Management**
- Track who's logged in
- Monitor shift times
- Role-based access control

## 🔧 Quick Customization

### Add Custom Images
1. Place images in `images/products/`
2. Use naming: `product_name.jpg`
3. Restart application

### Update Prices
1. Login as Admin/Manager
2. Go to Inventory tab
3. Edit product prices

### RFID Setup
1. Connect USB RFID reader
2. Update port in `RFIDReader.java`
3. Register cards in admin panel

## 🆘 Need Help?

### Common Issues
- **Won't start**: Check Java installation
- **No images**: Verify `images/products/` directory
- **RFID not working**: Check USB connection

### Get Support
- Check `README.md` for detailed instructions
- Review `activity_log.txt` for errors
- Test with different user accounts

## 📊 Daily Operations

### Opening
1. Start application
2. Login with your role
3. Check inventory levels
4. Review daily targets

### During Service
1. Process orders quickly
2. Customize as requested
3. Accept various payments
4. Track customer preferences

### Closing
1. Generate daily reports
2. Check cash drawer
3. Update inventory
4. Log out properly

## 💡 Pro Tips

### Speed Up Service
- Use RFID login for quick access
- Memorize product codes
- Customize common orders
- Keep images optimized

### Better Management
- Review reports daily
- Update product images regularly
- Monitor user activity
- Backup data weekly

### Customer Experience
- Use product images for visual appeal
- Offer customization options
- Track popular items
- Maintain clean interface

---

**Need more details?** See the full `README.md` for comprehensive documentation. 