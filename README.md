# Vehicle Rental Management System

A Java-based Vehicle Rental Management System developed using Maven and JavaFX.

The system allows managers to authenticate, view available vehicles, create and validate rentals, return vehicles, calculate invoices, apply late penalties, and manage different vehicle types using object-oriented principles and design patterns.

## Team Members

- Alaa Lubbadeh
- Yara Obeid

## Main Features

### Authentication

- Manager login using valid credentials.
- Invalid login handling.
- Manager logout.

### Vehicle Catalogue

- Display available vehicles only.
- Search vehicles by ID, type, brand, model, or plate number.
- Hide rented or unavailable vehicles.
- Refresh the available vehicle list.
- Support multiple vehicle types:
  - Car
  - Van
  - Truck
  - Motorcycle
  - Electric Vehicle

### Rental Operations

- Create a rental record.
- Connect the rental to a customer and a vehicle.
- Change the vehicle status to `RENTED`.
- Prevent double booking.
- Prevent duplicate rental IDs.
- Validate the rental period.
- Reject invalid rental requests.
- Update the dashboard after creating a rental.

### Vehicle-Specific Validation

- Trucks require a truck driving license.
- Motorcycle renters must be at least 18 years old.
- Electric vehicles require a valid battery check.
- The electric vehicle battery level must be at least 30%.
- Vehicle availability is verified before creating a rental.
- Rental duration cannot exceed 30 days.
- The rental end date must be after the start date.

### Rental Reminders

- Detect active rentals that are close to their end date.
- Display the number of rentals close to expiration on the dashboard.
- Notify customers about expiring rentals.
- Use the Observer Pattern for rental expiry notifications.
- Use Mockito to test the notification behavior.

### Returns and Billing

- Display active rentals only.
- Return rented vehicles.
- Change the vehicle status back to `AVAILABLE`.
- Close rental records.
- Validate the actual return date.
- Calculate the rental cost.
- Apply late return penalties.
- Generate an invoice containing:
  - Rental cost
  - Late penalty
  - Total amount

## JavaFX Graphical User Interface

The project includes a JavaFX graphical user interface with the following pages:

- Manager Login
- Dashboard
- Available Vehicles
- Create Rental
- Return Vehicle
- Generated Invoice

The dashboard displays dynamic information about:

- Available vehicles
- Active rentals
- Rentals close to expiration
- System status

The GUI is connected directly to the existing service and repository layers.

### GUI Workflow

```text
Login
→ Dashboard
→ Browse Available Vehicles
→ Create Rental
→ Return Vehicle
→ Generate Invoice
```

Rental and return operations automatically update the dashboard statistics and vehicle availability.

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

The Repository Pattern separates data access from business logic.

The project currently uses in-memory repository implementations:

- `InMemoryManagerRepository`
- `InMemoryVehicleRepository`
- `InMemoryRentalRepository`

Because the repositories are stored in memory, rental data is available while the application is running and is reset when the application is restarted.

## Project Architecture

The project is divided into the following packages:

```text
com.vehiclerental
├── gui
│   ├── controller
│   │   ├── LoginController
│   │   ├── DashboardController
│   │   ├── AvailableVehiclesController
│   │   ├── CreateRentalController
│   │   └── ReturnVehicleController
│   ├── GuiContext
│   └── VehicleRentalApp
├── model
├── repository
├── service
├── validation
├── pricing
├── notification
├── observer
└── util
```

### GUI Layer

Contains the JavaFX application, shared GUI context, page controllers, FXML files, and CSS styling.

Main GUI classes:

- `VehicleRentalApp`
- `GuiContext`
- `LoginController`
- `DashboardController`
- `AvailableVehiclesController`
- `CreateRentalController`
- `ReturnVehicleController`

### Model Layer

Contains the main system entities:

- `Vehicle`
- `Car`
- `Van`
- `Truck`
- `Motorcycle`
- `ElectricVehicle`
- `Customer`
- `Manager`
- `License`
- `Rental`
- `Invoice`

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
- JavaFX 17
- FXML
- CSS
- Maven
- JUnit 5
- Mockito
- JaCoCo
- SonarQube Cloud
- GitHub Actions
- PlantUML
- Git
- GitHub

## Requirements

Before running the project, make sure the following tools are available:

- Java Development Kit 17
- Maven, or Maven integration in IntelliJ IDEA
- IntelliJ IDEA or another Java IDE
- Internet access for downloading Maven dependencies

## How to Run the Application

### Default Login Credentials

```text
Username: admin
Password: 1234
```

### Using IntelliJ IDEA and Maven

1. Open the project in IntelliJ IDEA.
2. Wait for Maven dependencies to load.
3. Open the Maven tool window.
4. Reload all Maven projects.
5. Open:

```text
Plugins → javafx → javafx:run
```

6. Double-click `javafx:run`.

The JavaFX login page will open automatically.

### Using the Command Line

Run:

```bash
mvn clean javafx:run
```

The JavaFX application should be started through Maven so that the required JavaFX libraries are included correctly.

## GUI Validation

The Create Rental page validates:

- Required customer information.
- Positive rental and customer IDs.
- Duplicate rental IDs.
- Valid start and end dates.
- Rental duration of no more than 30 days.
- Vehicle availability.
- Truck driving license requirements.
- Minimum motorcycle rental age.
- Electric vehicle battery status.

The Return Vehicle page:

- Displays active rentals only.
- Validates the actual return date.
- Prevents returning an already closed rental.
- Closes the selected rental.
- Returns the vehicle to available status.
- Calculates the rental cost.
- Calculates late penalties.
- Displays the generated invoice.

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

The current test suite contains 21 passing tests:

```text
Failures: 0
Errors: 0
Skipped: 0
```

The JavaFX workflow was also tested manually using the following scenario:

```text
Login
→ View Available Vehicles
→ Create Rental
→ Check Dashboard Statistics
→ Return Vehicle
→ Generate Invoice
→ Confirm Vehicle Availability
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

Recorded JaCoCo results after refactoring:

| Metric | Result |
|---|---:|
| Instruction Coverage | 88% |
| Branch Coverage | 66% |
| Covered Classes | 35 of 37 |
| Covered Lines | 419 of 477 |

JavaFX GUI classes are tested manually and are excluded from the SonarQube new-code coverage calculation.

## SonarQube Cloud Analysis

SonarQube Cloud is integrated with the project through GitHub Actions.

The workflow automatically performs:

- Maven build.
- Unit test execution.
- JaCoCo coverage generation.
- SonarQube code-quality analysis.
- Pull Request quality checks.

Recorded overall SonarQube results after refactoring:

| Metric | Result |
|---|---:|
| Security Issues | 0 |
| Reliability Issues | 0 |
| Maintainability Issues | 7 |
| Overall Coverage | 83.6% |
| Duplications | 0.0% |
| Security Hotspots | 0 |

Recorded results on new code:

| Metric | Result |
|---|---:|
| New Code Coverage | At least 80% |
| Duplications | At most 3% |
| Quality Gate | Passed |

The JavaFX GUI Pull Request passed the SonarQube Quality Gate before being merged into the main branch.

## Continuous Integration

GitHub Actions runs automatically when:

- Code is pushed to the `master` branch.
- A Pull Request is opened.
- A Pull Request is updated.
- New commits are pushed to a Pull Request branch.

The workflow file is located at:

```text
.github/workflows/build.yml
```

A Pull Request is merged only after:

- Maven build passes.
- All tests pass.
- SonarQube analysis completes.
- The Quality Gate passes.
- No merge conflicts remain.

## Refactoring Improvements

The following improvements were applied during the refactoring phase:

- Reduced the cognitive complexity of `Main.java`.
- Extracted responsibilities into smaller methods.
- Replaced console output in `Main.java` with Java logging.
- Added explicit time-zone handling for date operations.
- Added an integration test for the main demonstration.
- Increased code coverage above 80%.
- Reduced reliability issues from 11 to 0.
- Reduced maintainability issues from 55 to 7.
- Added a JavaFX graphical user interface.
- Connected the GUI to the existing service and repository layers.
- Added dynamic dashboard statistics.
- Preserved the existing business behavior.

## Documentation

The project includes:

- Javadoc for project classes and methods.
- Updated PlantUML class diagram.
- JavaFX GUI documentation.
- Automated test reports.
- JaCoCo coverage reports.
- SonarQube Cloud quality analysis.
- GitHub Actions build results.

The updated UML files are available at:

```text
docs/uml/class-diagram.puml
docs/uml/softuml.png
```

## Project Status

- Build: Passing
- Tests: 21 Passing
- GUI Workflow: Working
- Javadoc Inspection: No Missing Javadoc
- Quality Gate: Passed
- Recorded Overall SonarQube Coverage: 83.6%
- Recorded JaCoCo Instruction Coverage: 88%
- Reliability Issues: 0
- Security Issues: 0