# Vehicle Rental Management System

A Java-based Vehicle Rental Management System developed using Maven.

The system allows managers to authenticate, view available vehicles, create rentals, prevent invalid bookings, return vehicles, calculate invoices, apply late penalties, and manage different vehicle types using object-oriented design principles and design patterns.

## Team Members

- Alaa Lubbadeh
- Yara obeid

## Main Features

### Authentication

- Manager login using valid credentials.
- Invalid login handling.
- Manager logout.

### Vehicle Catalogue

- Display available vehicles only.
- Hide rented and unavailable vehicles.
- Support multiple vehicle types:
  - Car
  - Van
  - Truck
  - Motorcycle
  - Electric Vehicle

### Rental Operations

- Create a rental record.
- Connect the rental to a customer and vehicle.
- Change the vehicle status to rented.
- Prevent double booking.
- Validate the rental period.
- Reject invalid rental requests.

### Vehicle-Specific Validation

- Trucks require a truck driving license.
- Motorcycle rentals require an eligible customer age.
- Electric vehicles require a valid battery check.
- Availability is verified before creating a rental.

### Rental Reminders

- Detect rentals that are close to their end date.
- Notify customers about expiring rentals.
- Use the Observer Pattern for expiry notifications.
- Use Mockito to test notification behavior.

### Returns and Billing

- Return rented vehicles.
- Change the vehicle status back to available.
- Close rental records.
- Calculate the rental cost.
- Apply late return penalties.
- Generate an invoice containing:
  - Rental cost
  - Late penalty
  - Total amount

## Design Patterns

### Strategy Pattern

The Strategy Pattern is used for:

- Rental validation rules.
- Vehicle pricing calculations.
- Late penalty calculations.

Validation strategies include:

- `AvailabilityValidationStrategy`
- `DurationValidationStrategy`
- `TruckLicenseValidationStrategy`
- `MotorcycleAgeValidationStrategy`
- `ElectricBatteryValidationStrategy`

Pricing strategies include:

- `DefaultPricingStrategy`
- `CarPricingStrategy`
- `TruckPricingStrategy`
- `ElectricVehiclePricingStrategy`
- `DefaultLatePenaltyStrategy`

### Observer Pattern

The Observer Pattern is used to notify customers when their rentals are close to expiration.

Main components:

- `RentalObserver`
- `RentalExpiryObserver`
- `ReminderService`
- `NotificationService`
- `EmailNotificationService`

### Repository Pattern

Repositories separate data access from the business logic.

The project currently uses in-memory repository implementations:

- `InMemoryManagerRepository`
- `InMemoryVehicleRepository`
- `InMemoryRentalRepository`

## Project Architecture

The project is divided into the following packages:

```text
com.vehiclerental
├── model
├── repository
├── service
├── validation
├── pricing
├── notification
├── observer
└── util
```

### Model Layer

Contains the main system entities:

- Vehicle
- Car
- Van
- Truck
- Motorcycle
- ElectricVehicle
- Customer
- Manager
- License
- Rental
- Invoice

### Repository Layer

Responsible for storing and retrieving system data.

### Service Layer

Contains the main business operations:

- `AuthService`
- `VehicleCatalogService`
- `RentalService`
- `ReminderService`
- `BillingService`
- `ReturnService`

### Validation Layer

Contains independent rental validation strategies.

### Pricing Layer

Contains rental pricing and late penalty strategies.

### Notification and Observer Layers

Responsible for rental expiry notifications.

### Utility Layer

Provides date operations through:

- `DateTimeProvider`
- `SystemDateTimeProvider`

## Technologies Used

- Java 17
- Maven
- JUnit 5
- Mockito
- JaCoCo
- SonarQube Cloud
- GitHub Actions
- PlantUML
- Git and GitHub

## Requirements

Before running the project, make sure the following tools are available:

- Java Development Kit 17
- Maven, or the Maven integration available in IntelliJ IDEA
- IntelliJ IDEA or another Java IDE

## How to Run the Application

The main entry point is:

```text
src/main/java/com/vehiclerental/Main.java
```

### Using IntelliJ IDEA

1. Open the project in IntelliJ IDEA.
2. Wait for Maven dependencies to load.
3. Open `Main.java`.
4. Run the `main` method.

Default manager credentials used by the demonstration are:

```text
Username: admin
Password: 1234
```

### Using Maven

Run:

```bash
mvn clean compile
```

Then run the main class through the IDE.

## Testing

The project contains unit and integration tests for the main services and business rules.

Main test classes include:

- `AuthServiceTest`
- `VehicleCatalogServiceTest`
- `RentalServiceTest`
- `ReminderServiceTest`
- `BillingServiceTest`
- `ReturnServiceTest`
- `VehicleTypeRulesTest`
- `MainTest`

Run all tests using:

```bash
mvn clean test
```

Run the full verification process using:

```bash
mvn clean verify
```

The current test suite contains 21 passing tests with:

```text
Failures: 0
Errors: 0
Skipped: 0
```

## Code Coverage

JaCoCo is used to generate the local code coverage report.

Run:

```bash
mvn clean verify
```

Then open:

```text
target/site/jacoco/index.html
```

Latest JaCoCo results after refactoring:

| Metric | Result |
|---|---:|
| Instruction Coverage | 88% |
| Branch Coverage | 66% |
| Covered Classes | 35 of 37 |
| Covered Lines | 419 of 477 |

## SonarQube Cloud Analysis

SonarQube Cloud is integrated with the project through GitHub Actions.

The workflow automatically performs:

- Maven build
- Unit tests
- JaCoCo coverage generation
- SonarQube code-quality analysis

Latest overall SonarQube results:

| Metric | Result |
|---|---:|
| Security Issues | 0 |
| Reliability Issues | 0 |
| Maintainability Issues | 7 |
| Overall Coverage | 83.6% |
| Duplications | 0.0% |
| Security Hotspots | 0 |

Latest results on new code:

| Metric | Result |
|---|---:|
| New Issues | 0 |
| New Code Coverage | 93.75% |
| Duplications | 0.0% |
| Quality Gate | Passed |

## Continuous Integration

GitHub Actions runs automatically when:

- Code is pushed to the main branch.
- A Pull Request is opened.
- A Pull Request is updated.

The workflow file is located at:

```text
.github/workflows/build.yml
```

## Refactoring Improvements

The following improvements were applied during the refactoring phase:

- Reduced the cognitive complexity of `Main.java`.
- Extracted responsibilities into smaller methods.
- Replaced console output with Java logging.
- Added explicit time-zone handling for date operations.
- Added an integration test for the main demonstration.
- Increased code coverage above 80%.
- Reduced reliability issues from 11 to 0.
- Reduced maintainability issues from 55 to 7.
- Preserved all existing system behavior.

## Documentation

The project contains Javadoc documentation for classes, methods, fields, parameters, and return values.

The updated UML class diagram is available at:

```text
docs/uml/class-diagram.puml
docs/uml/softuml.png
```

## Project Status

- Build: Passing
- Tests: Passing
- Quality Gate: Passed
- Overall SonarQube Coverage: 83.6%
- JaCoCo Instruction Coverage: 88%
- Reliability Issues: 0
- Security Issues: 0