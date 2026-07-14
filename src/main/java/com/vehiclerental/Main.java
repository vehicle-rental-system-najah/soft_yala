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
/**
 * Main class used to run and demonstrate the Vehicle Rental Management System.
 */
public class Main {
    /**
     * Starts the application and demonstrates the main system features.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        ManagerRepository managerRepository = new InMemoryManagerRepository();
        VehicleRepository vehicleRepository = new InMemoryVehicleRepository();
        RentalRepository rentalRepository = new InMemoryRentalRepository();

        AuthService authService = new AuthService(managerRepository);
        VehicleCatalogService catalogService = new VehicleCatalogService(vehicleRepository);

        List<RentalValidationStrategy> validations = new ArrayList<>();
        validations.add(new AvailabilityValidationStrategy());
        validations.add(new DurationValidationStrategy());
        validations.add(new TruckLicenseValidationStrategy());
        validations.add(new MotorcycleAgeValidationStrategy());
        validations.add(new ElectricBatteryValidationStrategy());

        RentalService rentalService = new RentalService(
                vehicleRepository,
                rentalRepository,
                validations
        );

        NotificationService notificationService = new EmailNotificationService();

        ReminderService reminderService = new ReminderService(
                rentalRepository,
                new SystemDateTimeProvider()
        );

        reminderService.addObserver(new RentalExpiryObserver(notificationService));

        BillingService billingService = new BillingService(
                new DefaultPricingStrategy(),
                new DefaultLatePenaltyStrategy()
        );

        ReturnService returnService = new ReturnService(
                rentalRepository,
                billingService
        );

        boolean loggedIn = authService.login("admin", "1234");

        if (loggedIn) {
            System.out.println("Login successful");
            System.out.println();

            System.out.println("Available vehicles before rental:");
            List<Vehicle> availableVehicles = catalogService.getAvailableVehicles();

            for (Vehicle vehicle : availableVehicles) {
                System.out.println(vehicle);
            }

            System.out.println();

            Customer customer = new Customer(
                    1,
                    "Lina Ahmad",
                    "0599000000",
                    "lina@example.com",
                    25,
                    new License("LIC-100", LicenseType.REGULAR)
            );

            Rental rental = rentalService.rentVehicle(
                    1,
                    customer,
                    1,
                    LocalDate.now(),
                    LocalDate.now().plusDays(1)
            );

            if (rental != null) {
                System.out.println("Rental created successfully");
                System.out.println(rental);
            } else {
                System.out.println("Rental was not created");
            }

            System.out.println();

            System.out.println("Trying to rent the same vehicle again:");

            Rental secondRental = rentalService.rentVehicle(
                    2,
                    customer,
                    1,
                    LocalDate.now(),
                    LocalDate.now().plusDays(3)
            );

            if (secondRental != null) {
                System.out.println("Second rental created successfully");
                System.out.println(secondRental);
            } else {
                System.out.println("Second rental rejected");
            }

            System.out.println();

            System.out.println("Checking rental expiry reminders:");
            reminderService.checkExpiringRentals(1);

            System.out.println();

            System.out.println("Returning vehicle with late return:");
            Invoice invoice = returnService.returnVehicle(
                    1,
                    LocalDate.now().plusDays(3)
            );

            if (invoice != null) {
                System.out.println("Vehicle returned successfully");
                System.out.println(invoice);
            }

            System.out.println();

            System.out.println("Trying to rent a truck with regular license:");

            Rental rejectedTruckRental = rentalService.rentVehicle(
                    3,
                    customer,
                    4,
                    LocalDate.now(),
                    LocalDate.now().plusDays(2)
            );

            if (rejectedTruckRental == null) {
                System.out.println("Truck rental rejected");
            }

            System.out.println();

            Customer truckCustomer = new Customer(
                    2,
                    "Omar Saleh",
                    "0599111111",
                    "omar@example.com",
                    30,
                    new License("TRK-200", LicenseType.TRUCK)
            );

            System.out.println("Trying to rent a truck with truck license:");

            Rental truckRental = rentalService.rentVehicle(
                    4,
                    truckCustomer,
                    4,
                    LocalDate.now(),
                    LocalDate.now().plusDays(2)
            );

            if (truckRental != null) {
                System.out.println("Truck rental created successfully");
                System.out.println(truckRental);
            }

            System.out.println();

            System.out.println("Trying to rent an electric vehicle:");

            Rental electricRental = rentalService.rentVehicle(
                    5,
                    customer,
                    6,
                    LocalDate.now(),
                    LocalDate.now().plusDays(2)
            );

            if (electricRental != null) {
                System.out.println("Electric vehicle rental created successfully");
                System.out.println(electricRental);
            }

            System.out.println();

            System.out.println("Available vehicles at the end:");
            List<Vehicle> finalAvailableVehicles = catalogService.getAvailableVehicles();

            for (Vehicle vehicle : finalAvailableVehicles) {
                System.out.println(vehicle);
            }

            System.out.println();

            authService.logout();
            System.out.println("Manager logged out");
        } else {
            System.out.println("Invalid username or password");
        }
    }
}