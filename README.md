# ☕ CoffeeShop POS

A Java Swing-based Point of Sale system designed for coffee shops, with a clean fullscreen UI, tile-based product selection, and basic transaction handling.

---

## ✅ Features

- 🔐 **User login system** (hardcoded: `admin` / `1234`)
- 🧱 **Tile-style coffee menu** with 12 commonly sold drinks
- ➕ **Smart cart logic** — repeated items appear as `Name x2`, `x3`, etc.
- 📐 **Fullscreen layout** — optimized for touchscreens and kiosk setups
- 🧾 **Receipt pop-up** — prints receipt on checkout
- 💰 **Formatted totals** — displayed as `₱#,###.00`
- 🗑️ **Remove selected items** from cart

---

## 🛠️ Setup Instructions

### Requirements:
- Java JDK 8 or higher
- Git (optional)

### Run Locally:
```bash
git clone https://github.com/Einsbernnn/CoffeeShop_POS.git
cd CoffeeShop_POS
javac POSLogin.java POSMain.java POSList.java
java POSLogin  
