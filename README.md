# Vehicle Rental Management System

This project is a Vehicle Rental Management System developed in Java using Maven.  
The system allows managers to log in, view available vehicles, rent vehicles, return vehicles, calculate rental costs, apply late return penalties, and handle different vehicle types with specific rules.

## Project Features

### Sprint 1: Authentication and Vehicle Catalog
- Manager login with valid credentials.
- Manager logout.
- Display available vehicles only.
- Hide rented or unavailable vehicles.

### Sprint 2: Rental Operations
- Create a rental record.
- Change vehicle status to rented.
- Prevent double booking.
- Validate rental duration.

### Sprint 3: Notifications and Mocking
- Generate rental expiry reminders.
- Use notification service for sending reminders.
- Apply Observer Pattern.
- Use Mockito in testing the reminder service.

### Sprint 4: Returns and Billing
- Return rented vehicles.
- Change vehicle status back to available.
- Close rental records.
- Calculate rental cost.
- Apply late return penalty.

### Sprint 5: Vehicle Types and Polymorphism
- Support multiple vehicle types:
    - Car
    - Motorcycle
    - Van
    - Truck
    - Electric Vehicle
- Apply type-specific rules:
    - Trucks require a truck license.
    - Motorcycles require age validation.
    - Electric vehicles require battery checks.

## Design Patterns Used

### Strategy Pattern
Used for rental pricing and rental validation rules.

### Observer Pattern
Used for rental expiry notifications.

## Technologies Used

- Java 17
- Maven
- JUnit 5
- Mockito
- JaCoCo
- GitHub Actions

## How to Run the Project

Run the `Main` class:

```text
src/main/java/com/vehiclerental/Main.java
```
## How to Run Tests

Tests can be run using Maven:

```bash
mvn clean test
```

The project also includes GitHub Actions to run the Maven build and tests automatically.

## Testing

Unit tests were created for the main services:

- AuthServiceTest
- VehicleCatalogServiceTest
- RentalServiceTest
- ReminderServiceTest
- BillingServiceTest
- ReturnServiceTest
- VehicleTypeRulesTest

## Code Coverage

JaCoCo was used to generate the code coverage report after running the test suite.