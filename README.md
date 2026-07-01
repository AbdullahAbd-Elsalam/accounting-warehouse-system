# Accounting Warehouse System

An enterprise-level **Accounting and Warehouse Management System** built with **Java**, **Spring Boot**, **Spring Data JPA**, and **MySQL**.

This project is inspired by real-world accounting and warehouse workflows used in contracting and construction companies. It aims to provide a scalable backend architecture for managing suppliers, purchase invoices, inventory, warehouse operations, and accounting transactions through RESTful REST APIs.

The application is currently under active development and is being built incrementally by following clean architecture principles and enterprise software development practices.

---

# Project Objectives

The main objective of this project is to simulate a real Enterprise Resource Planning (ERP) backend system by integrating accounting and warehouse management into one application.

The system is designed to:

* Manage suppliers and purchase invoices.
* Calculate VAT automatically.
* Manage warehouse inventory transactions.
* Track materials issued to projects.
* Generate accounting journal entries.
* Maintain a clean, scalable, and maintainable architecture.
* Apply enterprise software development best practices.

---

# Technologies

* Java 17
* Spring Boot
* Spring Data JPA (Hibernate)
* MySQL
* Maven
* Lombok
* REST APIs
* IntelliJ IDEA
* Git & GitHub
* Postman

---

# Architecture

The project follows the Layered Architecture pattern.

```text
Controller
      ↓
Service
      ↓
Repository
      ↓
Database
```

The application separates responsibilities into:

* Controllers
* Services
* Repositories
* Entities
* DTOs
* Enums
* Exception Handling

This architecture improves maintainability, scalability, and testability.

---

# Current Features

## Supplier Module

* Create Supplier
* Update Supplier
* Delete Supplier
* Get Supplier by ID
* Get All Suppliers

## Purchase Invoice Module

* Create Purchase Invoice
* Update Purchase Invoice
* Retrieve Invoice by ID
* Multiple Invoice Items
* Automatic VAT Calculation
* Supplier Relationship
* Invoice Total Calculation

---

# Database Design

Current entities include:

* Supplier
* Invoice
* Invoice Item

Relationships:

* One Supplier → Many Invoices
* One Invoice → Many Invoice Items

---

# System Design

## Class Diagram

> Add the image here.

```text
docs/class-diagram.png
```

---

## Use Case Diagram

> Add the image here.

```text
docs/use-case-diagram.png
```

---

## Sequence Diagram

> Add the image here.

```text
docs/sequence-create-invoice.png
```

---

# Business Workflow

The current business workflow follows the accounting process used in construction companies.

```text
Supplier
      │
      ▼
Purchase Invoice
      │
      ▼
VAT Calculation
      │
      ▼
Invoice Storage
```

Future versions will extend this workflow to include warehouse operations and accounting postings.

---

# REST API

## Supplier APIs

| Method | Endpoint            |
| ------ | ------------------- |
| POST   | /api/suppliers      |
| GET    | /api/suppliers      |
| GET    | /api/suppliers/{id} |
| PUT    | /api/suppliers/{id} |
| DELETE | /api/suppliers/{id} |

## Purchase Invoice APIs

| Method | Endpoint           |
| ------ | ------------------ |
| POST   | /api/invoices      |
| GET    | /api/invoices/{id} |
| PUT    | /api/invoices/{id} |

---

# Project Structure

```text
src
├── controller
├── service
├── repository
├── entity
├── dto
├── enums
├── exception
├── config
└── resources
```

---

# Roadmap

## Version 0.1

* Supplier Management
* Purchase Invoice Management
* Invoice Items
* VAT Calculation
* REST APIs

## Version 0.2

* Warehouse Module
* Warehouse Receipt
* Warehouse Transactions
* Intermediate Account

## Version 0.3

* Material Issue
* Project Material Consumption
* Inventory Balance

## Version 0.4

* Chart of Accounts
* Journal Entries
* Financial Posting

## Version 1.0

* Authentication & Authorization
* Role-Based Access Control
* Reports
* Dashboard
* Swagger / OpenAPI Documentation
* Docker Deployment

---

# Project Status

**Current Version:** `v0.1.0`

**Status:** 🚧 Under Active Development

This project is continuously evolving, and new modules will be added in future releases.

---

# Author

**Abdullah Abd-Elsalam**

Backend Developer | Java | Spring Boot

GitHub: https://github.com/AbdullahAbd-Elsalam
