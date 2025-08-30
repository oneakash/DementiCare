# DementiCare Platform - Spring Boot Microservices Backend

## Architecture Overview

The DementiCare platform backend is built using a microservices architecture with Spring Boot and Spring Cloud. The system consists of the following services:

### Core Services

1. **Eureka Server** (Port 8761)
   - Service discovery and registration
   - Manages service instances and health checks

2. **API Gateway** (Port 8080)
   - Single entry point for all client requests
   - Request routing and load balancing
   - Cross-cutting concerns (CORS, authentication)

3. **User Service** (Port 8081)
   - User registration and authentication
   - User profile management
   - Role-based access control (Student, Caregiver, Admin)
   - JWT token generation and validation

4. **Task Service** (Port 8082)
   - Task creation and management
   - Visual schedule management
   - Progress tracking
   - Task assignment and completion

5. **Content Service** (Port 8083)
   - Flashcard management with spaced repetition
   - Social story creation and management
   - Content categorization and difficulty levels

## Technology Stack

- **Framework**: Spring Boot 3.2.0
- **Security**: Spring Security with JWT
- **Database**: H2 (in-memory for development)
- **Service Discovery**: Netflix Eureka
- **API Gateway**: Spring Cloud Gateway
- **Documentation**: OpenAPI/Swagger
- **Build Tool**: Maven

## Security Features

- JWT-based authentication
- Role-based authorization (RBAC)
- Method-level security
- CORS configuration
- Password encryption with BCrypt

## Database Schema

### User Service
- Users table with roles, preferences, and profile information
- Support for multiple languages and accessibility settings

### Task Service
- Tasks table with scheduling, visual aids, and progress tracking
- Support for different task types (visual schedule, flashcards, etc.)

### Content Service
- Flashcards with spaced repetition algorithm
- Social stories with step-by-step content
- Content categorization and difficulty levels

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher

### Running the Services

1. **Start Eureka Server**
   ```bash
   cd backend/eureka-server
   mvn spring-boot:run
   ```

2. **Start User Service**
   ```bash
   cd backend/user-service
   mvn spring-boot:run
   ```

3. **Start Task Service**
   ```bash
   cd backend/task-service
   mvn spring-boot:run
   ```

4. **Start Content Service**
   ```bash
   cd backend/content-service
   mvn spring-boot:run
   ```

5. **Start API Gateway**
   ```bash
   cd backend/api-gateway
   mvn spring-boot:run
   ```

### Service URLs

- **Eureka Dashboard**: http://localhost:8761
- **API Gateway**: http://localhost:8080
- **User Service**: http://localhost:8081
- **Task Service**: http://localhost:8082
- **Content Service**: http://localhost:8083

### API Endpoints

#### Authentication
- `POST /api/auth/login` - User login
- `POST /api/auth/register` - User registration
- `POST /api/auth/refresh` - Token refresh

#### User Management
- `GET /api/users/{id}` - Get user by ID
- `PUT /api/users/{id}` - Update user profile
- `GET /api/users/role/{role}` - Get users by role

#### Task Management
- `POST /api/tasks` - Create new task
- `GET /api/tasks/student/{studentId}` - Get student tasks
- `PUT /api/tasks/{id}/status` - Update task status
- `GET /api/tasks/student/{studentId}/today` - Get today's tasks

#### Content Management
- `POST /api/flashcards` - Create flashcard
- `GET /api/flashcards/student/{studentId}` - Get student flashcards
- `POST /api/stories` - Create social story
- `GET /api/stories/category/{category}` - Get stories by category

## Development Notes

### Database Configuration
- Currently using H2 in-memory database for development
- For production, configure PostgreSQL or MySQL
- Each service has its own database instance

### Security Configuration
- JWT tokens expire after 24 hours
- Passwords are encrypted using BCrypt
- Role-based access control implemented at method level

### Microservices Communication
- Services communicate through Eureka service discovery
- Feign clients for inter-service communication
- Circuit breaker pattern can be added for resilience

### Future Enhancements
- Add notification service for real-time updates
- Implement caching with Redis
- Add monitoring with Spring Boot Actuator
- Implement distributed tracing
- Add API documentation with Swagger/OpenAPI