# 🧠 Stock Analyst

A full-stack **Spring Boot** application that allows users to manage a personalized **stock watchlist**.  
Each user can add or remove stocks, view their current watchlist, and manage their account.  
Built with a focus on **clean architecture**, **security**, and **scalability** — forming the foundation for future expansion into analytics and live stock data.

---

## 🚀 Features

- **User Management** — Each user has their own dedicated watchlist (1:1 relationship)
- **Watchlist CRUD Operations** — Add, remove, and view stocks on a user’s list
- **Flyway Migrations** — Database version control and schema management
- **RESTful API** — Modular and testable backend endpoints
- **Spring Security (Planned)** — Foundation for JWT-based authentication
- **Scalable Architecture** — Domain-driven structure for future growth (e.g., multiple watchlists per user)
- **PostgreSQL Integration** — Persistent data storage with JPA + Hibernate

---

## 🧩 Tech Stack

**Backend Frameworks:**

- Java 21
- Spring Boot 3+
- Spring Data JPA
- Spring Web
- Spring Security (JWT Authentication)

**Database & Migrations:**

- PostgreSQL
- Flyway

**Build & Dependencies:**

- Maven

**Testing:**

- JUnit 5
- Spring Boot Test

**Dev Tools:**

- IntelliJ / Eclipse
- Postman (API testing)
- Git & GitHub

---

## 🏗️ Project Structure

src/
├── main/java/com/yourname/stock/
│ ├── controller/ # REST Controllers
│ ├── model/ # JPA Entities
│ ├── repository/ # Data Repositories
│ ├── service/ # Business Logic
│ └── StockAnalystMvpApplication.java
├── main/resources/
│ ├── application.yml # Config
│ └── db/migration/ # Flyway SQL migrations
└── test/java/... # Unit & integration tests
