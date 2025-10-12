# Electronic-Bazaar (Monolith)

## Requirements
- Java 21
- MySQL running on localhost:3306
- (Optional) Gradle, or use the included gradlew wrapper

## Setup
1. Create database:
```sql
CREATE DATABASE electronic_bazaar;
```
2. Update `src/main/resources/application.yml` with DB credentials and `jwt.secret`.
3. Run:
```bash
./gradlew bootRun
```

## Endpoints
- POST /api/auth/register
- POST /api/auth/login
- GET /api/products
- POST /api/products
- GET /api/customers
- POST /api/orders

Swagger UI: http://localhost:8080/swagger-ui.html
Actuator health: http://localhost:8080/actuator/health
