
# System of Record

A RESTful backend application for managing employee and department records, built using Java, Spring Boot, and MySQL.

## Features

- User registration and login using JWT authentication
- Password hashing with BCrypt
- Role-based access control for ADMIN and USER roles
- Employee CRUD operations
- Department CRUD operations
- Employee search by name
- Employee change history
- Audit logging for important operations
- Dashboard with employee, department, and user counts
- Request validation and centralized exception handling
- Application logging with SLF4J
- Swagger/OpenAPI documentation
- Spring Boot Actuator health, info, and metrics endpoints
- Automated application context test

## Technology Stack

- Java 17
- Spring Boot
- Spring Data JPA
- Spring Security
- JSON Web Tokens (JWT)
- MySQL
- Maven
- Swagger / OpenAPI
- Spring Boot Actuator
- JUnit 5

## Prerequisites

Install the following before running the application:

- Java 17
- Maven
- MySQL Server

## Database Setup

Create the database in MySQL:

```sql
CREATE DATABASE sor_db;
```

Configure your database username and password in `application.properties`.

Do not commit real database passwords or JWT secrets to GitHub.

## Configuration

The application uses environment variable `JWT_SECRET` for JWT signing.

Set `JWT_SECRET` in your IDE run configuration or environment before starting the application.

Check `src/main/resources/application.properties` for the database connection and server port.

## Run the Application

From the project root, run:

```bash
mvn spring-boot:run
```

The API runs on:

http://localhost:8081

## API Documentation

After starting the application, open:

http://localhost:8081/swagger-ui/index.html

## Main API Endpoints

| Method | Endpoint | Purpose |
|---|---|---|
| POST | `/api/auth/register` | Register a user |
| POST | `/api/auth/login` | Log in and receive a JWT |
| GET | `/api/employees` | Retrieve employees |
| POST | `/api/employees` | Create an employee |
| GET | `/api/employees/{id}` | Retrieve an employee |
| PUT | `/api/employees/{id}` | Update an employee |
| DELETE | `/api/employees/{id}` | Delete an employee |
| GET | `/api/employees/search?name=John` | Search employees |
| GET | `/api/departments` | Retrieve departments |
| GET | `/api/dashboard` | Retrieve dashboard counts |
| GET | `/api/audit-logs` | Retrieve audit logs |
| GET | `/api/health` | Application health endpoint |

Most endpoints require a valid JWT. Administrative operations require the appropriate role.

## Run Tests

Run the automated tests:

```bash
mvn test
```

## Project Purpose

This project demonstrates backend development concepts including REST APIs, database persistence, authentication, authorization, validation, auditing, API documentation, and application monitoring.
