# 📦 Product Management System (Full Stack)

A scalable **full-stack product management platform** built using **Spring Boot and React.js**, designed to handle product operations, inventory tracking, and intelligent recommendations using AI.

---

## 🚀 Overview

This application enables businesses to efficiently manage products, inventory, and orders through a modern UI and robust backend architecture. It also includes an **AI-powered bilingual assistant (Tamil & English)** to enhance user interaction and product discovery.

---

## ✨ Features

* 🔹 Product Management (CRUD operations)
* 🔹 Category-based filtering and search
* 🔹 Inventory dashboard with stock tracking & alerts
* 🔹 Order processing system
* 🔹 Secure authentication using JWT
* 🔹 RESTful API integration
* 🔹 AI-powered product assistant (Tamil & English)
* 🔹 Responsive frontend UI

---

## 🛠 Tech Stack

### 🔹 Backend

* Java
* Spring Boot
* Spring Data JPA
* REST API
* JWT Authentication

### 🔹 Frontend

* React.js
* HTML, CSS, JavaScript

### 🔹 Database

* MySQL

### 🔹 AI Integration

* Claude AI (for bilingual assistant)

### 🔹 Tools

* Postman
* Git & GitHub

---

## 📂 Project Structure

```id="3s1vpt"
ProductManagement/
│
├── backend/ (Spring Boot)
│   ├── controller
│   ├── service
│   ├── repository
│   ├── model
│   └── config
│
├── frontend/ (React.js)
│   ├── components
│   ├── pages
│   └── services
```

---

## ⚙️ Setup & Installation

### 1️⃣ Clone the repository

```bash id="b6y8l3"
git clone https://github.com/safeekaram012-cpu/productmanagement.git
cd productmanagement
```

---

### 2️⃣ Backend Setup (Spring Boot)

```bash id="2l4xtj"
cd ProductManagement
```

Update `application.properties`:

```properties id="nxmr79"
spring.datasource.url=jdbc:mysql://localhost:3306/product_db
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Run backend:

```bash id="f0m2pj"
mvn spring-boot:run
```

---

### 3️⃣ Frontend Setup (React)

```bash id="0g9k1m"
cd frontend
npm install
npm start
```

---

## 🔐 Authentication

* Implemented using **JWT Authentication**
* Secures API endpoints
* Supports role-based access control

---

## 🤖 AI Assistant

* Integrated with **Claude AI**
* Supports **Tamil & English**
* Helps users:

  * Explore products
  * Get recommendations
  * Improve user engagement

---

## 🔗 API Highlights

| Method | Endpoint       | Description        |
| ------ | -------------- | ------------------ |
| GET    | /products      | Fetch all products |
| POST   | /products      | Add new product    |
| PUT    | /products/{id} | Update product     |
| DELETE | /products/{id} | Delete product     |

---

## 📈 Key Highlights

* Clean **Controller-Service-Repository architecture**
* Scalable backend design using Spring Boot
* Real-world full-stack integration
* AI-powered enhancement (unique feature)

---

## 🧪 Testing

* API tested using **Postman**
* Validated CRUD operations and authentication flows

---

## 🔮 Future Enhancements

* Payment integration
* Advanced analytics dashboard
* Microservices architecture
* Cloud deployment (AWS / Docker)

---

## 👨‍💻 Author

**Mohamed Safeek**

* GitHub: https://github.com/safeekaram012-cpu
* LinkedIn: https://linkedin.com/in/mohamed-safeek-319624373

---

## ⭐ Contribution

Contributions are welcome! Feel free to fork and submit pull requests.

---

## 📜 License

This project is open-source and available under the MIT License.
