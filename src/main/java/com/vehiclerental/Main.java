package com.vehiclerental;

import com.vehiclerental.model.Customer;
import com.vehiclerental.model.Invoice;
import com.vehiclerental.model.License;
import com.vehiclerental.model.LicenseType;
import com.vehiclerental.model.Rental;
import com.vehiclerental.model.Vehicle;
import com.vehiclerental.notification.EmailNotificationService;
import com.vehiclerental.notification.NotificationService;
import com.vehiclerental.observer.RentalExpiryObserver;
import com.vehiclerental.pricing.DefaultLatePenaltyStrategy;
import com.vehiclerental.pricing.DefaultPricingStrategy;
import com.vehiclerental.repository.InMemoryManagerRepository;
import com.vehiclerental.repository.InMemoryRentalRepository;
import com.vehiclerental.repository.InMemoryVehicleRepository;
import com.vehiclerental.repository.ManagerRepository;
import com.vehiclerental.repository.RentalRepository;
import com.vehiclerental.repository.VehicleRepository;
import com.vehiclerental.service.AuthService;
import com.vehiclerental.service.BillingService;
import com.vehiclerental.service.ReminderService;
import com.vehiclerental.service.RentalService;
import com.vehiclerental.service.ReturnService;
import com.vehiclerental.service.VehicleCatalogService;
import com.vehiclerental.util.SystemDateTimeProvider;
import com.vehiclerental.validation.AvailabilityValidationStrategy;
import com.vehiclerental.validation.DurationValidationStrategy;
import com.vehiclerental.validation.ElectricBatteryValidationStrategy;
import com.vehiclerental.validation.MotorcycleAgeValidationStrategy;
import com.vehiclerental.validation.RentalValidationStrategy;
import com.vehiclerental.validation.TruckLicenseValidationStrategy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
public class Main {
    private static final Logger LOGGER =
            Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        ManagerRepository managerRepository = new InMemoryManagerRepository();
        VehicleRepository vehicleRepository = new InMemoryVehicleRepository();
        RentalRepository rentalRepository = new InMemoryRentalRepository();

        AuthService authService = new AuthService(managerRepository);
        VehicleCatalogService catalogService =
                new VehicleCatalogService(vehicleRepository);

        RentalService rentalService = new RentalService(
                vehicleRepository,
                rentalRepository,
                createValidationStrategies()
        );

        ReminderService reminderService = createReminderService(rentalRepository);

        BillingService billingService = new BillingService(
                new DefaultPricingStrategy(),
                new DefaultLatePenaltyStrategy()
        );

        ReturnService returnService = new ReturnService(
                rentalRepository,
                billingService
        );

        if (!authService.login("admin", "1234")) {
            log("Invalid username or password");
            return;
        }

        runRentalDemonstration(
                catalogService,
                rentalService,
                reminderService,
                returnService
        );

        authService.logout();
        log("Manager logged out");
    }

    private static List<RentalValidationStrategy> createValidationStrategies() {
        List<RentalValidationStrategy> validations = new ArrayList<>();

        validations.add(new AvailabilityValidationStrategy());
        validations.add(new DurationValidationStrategy());
        validations.add(new TruckLicenseValidationStrategy());
        validations.add(new MotorcycleAgeValidationStrategy());
        validations.add(new ElectricBatteryValidationStrategy());

        return validations;
    }

    private static ReminderService createReminderService(
            RentalRepository rentalRepository
    ) {
        NotificationService notificationService =
                new EmailNotificationService();

        ReminderService reminderService = new ReminderService(
                rentalRepository,
                new SystemDateTimeProvider()
        );

        reminderService.addObserver(
                new RentalExpiryObserver(notificationService)
        );

        return reminderService;
    }

    private static void runRentalDemonstration(
            VehicleCatalogService catalogService,
            RentalService rentalService,
            ReminderService reminderService,
            ReturnService returnService
    ) {
        log("Login successful");
        log("");

        displayAvailableVehicles(
                catalogService,
                "Available vehicles before rental:"
        );

        Customer customer = createRegularCustomer();

        demonstrateRegularRental(rentalService, customer);
        demonstrateDoubleBooking(rentalService, customer);
        demonstrateExpiryReminder(reminderService);
        demonstrateVehicleReturn(returnService);
        demonstrateRejectedTruckRental(rentalService, customer);
        demonstrateAcceptedTruckRental(rentalService);
        demonstrateElectricVehicleRental(rentalService, customer);

        displayAvailableVehicles(
                catalogService,
                "Available vehicles at the end:"
        );
    }

    private static Customer createRegularCustomer() {
        return new Customer(
                1,
                "Lina Ahmad",
                "0599000000",
                "lina@example.com",
                25,
                new License("LIC-100", LicenseType.REGULAR)
        );
    }

    private static Customer createTruckCustomer() {
        return new Customer(
                2,
                "Omar Saleh",
                "0599111111",
                "omar@example.com",
                30,
                new License("TRK-200", LicenseType.TRUCK)
        );
    }

    private static void displayAvailableVehicles(
            VehicleCatalogService catalogService,
            String heading
    ) {
        log(heading);

        List<Vehicle> availableVehicles =
                catalogService.getAvailableVehicles();

        for (Vehicle vehicle : availableVehicles) {
            log(vehicle);
        }

        log("");
    }

    private static void demonstrateRegularRental(
            RentalService rentalService,
            Customer customer
    ) {
        Rental rental = rentalService.rentVehicle(
                1,
                customer,
                1,
                LocalDate.now(),
                LocalDate.now().plusDays(1)
        );

        if (rental != null) {
            log("Rental created successfully");
            log(rental);
        } else {
            log("Rental was not created");
        }

        log("");
    }

    private static void demonstrateDoubleBooking(
            RentalService rentalService,
            Customer customer
    ) {
        log("Trying to rent the same vehicle again:");

        Rental secondRental = rentalService.rentVehicle(
                2,
                customer,
                1,
                LocalDate.now(),
                LocalDate.now().plusDays(3)
        );

        if (secondRental != null) {
            log("Second rental created successfully");
            log(secondRental);
        } else {
            log("Second rental rejected");
        }

        log("");
    }

    private static void demonstrateExpiryReminder(
            ReminderService reminderService
    ) {
        log("Checking rental expiry reminders:");
        reminderService.checkExpiringRentals(1);
        log("");
    }

    private static void demonstrateVehicleReturn(
            ReturnService returnService
    ) {
        log("Returning vehicle with late return:");

        Invoice invoice = returnService.returnVehicle(
                1,
                LocalDate.now().plusDays(3)
        );

        if (invoice != null) {
            log("Vehicle returned successfully");
            log(invoice);
        }

        log("");
    }

    private static void demonstrateRejectedTruckRental(
            RentalService rentalService,
            Customer customer
    ) {
        log(
                "Trying to rent a truck with regular license:"
        );

        Rental rejectedTruckRental = rentalService.rentVehicle(
                3,
                customer,
                4,
                LocalDate.now(),
                LocalDate.now().plusDays(2)
        );

        if (rejectedTruckRental == null) {
            log("Truck rental rejected");
        }

        log("");
    }

    private static void demonstrateAcceptedTruckRental(
            RentalService rentalService
    ) {
        Customer truckCustomer = createTruckCustomer();

        log(
                "Trying to rent a truck with truck license:"
        );

        Rental truckRental = rentalService.rentVehicle(
                4,
                truckCustomer,
                4,
                LocalDate.now(),
                LocalDate.now().plusDays(2)
        );

        if (truckRental != null) {
            log("Truck rental created successfully");
            log(truckRental);
        }

        log("");
    }

    private static void demonstrateElectricVehicleRental(
            RentalService rentalService,
            Customer customer
    ) {
        log("Trying to rent an electric vehicle:");

        Rental electricRental = rentalService.rentVehicle(
                5,
                customer,
                6,
                LocalDate.now(),
                LocalDate.now().plusDays(2)
        );

        if (electricRental != null) {
            log(
                    "Electric vehicle rental created successfully"
            );
            log(electricRental);
        }

        log("");
    }
    private static void log(Object message) {
        LOGGER.info(String.valueOf(message));
    }
}