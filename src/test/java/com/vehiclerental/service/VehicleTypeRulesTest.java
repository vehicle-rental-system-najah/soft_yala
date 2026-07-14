package com.vehiclerental.service;

import com.vehiclerental.model.Customer;
import com.vehiclerental.model.ElectricVehicle;
import com.vehiclerental.model.License;
import com.vehiclerental.model.LicenseType;
import com.vehiclerental.model.Motorcycle;
import com.vehiclerental.model.Rental;
import com.vehiclerental.model.Truck;
import com.vehiclerental.repository.InMemoryRentalRepository;
import com.vehiclerental.repository.RentalRepository;
import com.vehiclerental.repository.VehicleRepository;
import com.vehiclerental.validation.AvailabilityValidationStrategy;
import com.vehiclerental.validation.DurationValidationStrategy;
import com.vehiclerental.validation.ElectricBatteryValidationStrategy;
import com.vehiclerental.validation.MotorcycleAgeValidationStrategy;
import com.vehiclerental.validation.RentalValidationStrategy;
import com.vehiclerental.validation.TruckLicenseValidationStrategy;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTypeRulesTest {

    private RentalService createRentalService(VehicleRepository vehicleRepository,
                                              RentalRepository rentalRepository) {
        List<RentalValidationStrategy> validations = new ArrayList<>();
        validations.add(new AvailabilityValidationStrategy());
        validations.add(new DurationValidationStrategy());
        validations.add(new TruckLicenseValidationStrategy());
        validations.add(new MotorcycleAgeValidationStrategy());
        validations.add(new ElectricBatteryValidationStrategy());

        return new RentalService(vehicleRepository, rentalRepository, validations);
    }

    @Test
    void truckRentalShouldFailWithRegularLicense() {
        Truck truck = new Truck(1, "PAL-400", "Volvo", "FH", 90.0, 5000);
        VehicleRepository vehicleRepository = () -> List.of(truck);
        RentalRepository rentalRepository = new InMemoryRentalRepository();

        RentalService rentalService = createRentalService(vehicleRepository, rentalRepository);

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
                LocalDate.now().plusDays(2)
        );

        assertNull(rental);
    }

    @Test
    void truckRentalShouldPassWithTruckLicense() {
        Truck truck = new Truck(1, "PAL-400", "Volvo", "FH", 90.0, 5000);
        VehicleRepository vehicleRepository = () -> List.of(truck);
        RentalRepository rentalRepository = new InMemoryRentalRepository();

        RentalService rentalService = createRentalService(vehicleRepository, rentalRepository);

        Customer customer = new Customer(
                1,
                "Omar Saleh",
                "0599111111",
                "omar@example.com",
                30,
                new License("TRK-200", LicenseType.TRUCK)
        );

        Rental rental = rentalService.rentVehicle(
                1,
                customer,
                1,
                LocalDate.now(),
                LocalDate.now().plusDays(2)
        );

        assertNotNull(rental);
    }

    @Test
    void motorcycleRentalShouldFailForUnderageCustomer() {
        Motorcycle motorcycle = new Motorcycle(1, "PAL-500", "Honda", "CBR", 20.0, 150);
        VehicleRepository vehicleRepository = () -> List.of(motorcycle);
        RentalRepository rentalRepository = new InMemoryRentalRepository();

        RentalService rentalService = createRentalService(vehicleRepository, rentalRepository);

        Customer customer = new Customer(1, "Ali", "0599000000", 17);

        Rental rental = rentalService.rentVehicle(
                1,
                customer,
                1,
                LocalDate.now(),
                LocalDate.now().plusDays(2)
        );

        assertNull(rental);
    }

    @Test
    void electricVehicleRentalShouldFailWhenBatteryIsNotChecked() {
        ElectricVehicle electricVehicle = new ElectricVehicle(
                1,
                "PAL-600",
                "Tesla",
                "Model 3",
                80.0,
                80,
                false
        );

        VehicleRepository vehicleRepository = () -> List.of(electricVehicle);
        RentalRepository rentalRepository = new InMemoryRentalRepository();

        RentalService rentalService = createRentalService(vehicleRepository, rentalRepository);

        Customer customer = new Customer(1, "Lina Ahmad", "0599000000", 25);

        Rental rental = rentalService.rentVehicle(
                1,
                customer,
                1,
                LocalDate.now(),
                LocalDate.now().plusDays(2)
        );

        assertNull(rental);
    }

    @Test
    void electricVehicleRentalShouldPassWhenBatteryIsChecked() {
        ElectricVehicle electricVehicle = new ElectricVehicle(
                1,
                "PAL-600",
                "Tesla",
                "Model 3",
                80.0,
                80,
                true
        );

        VehicleRepository vehicleRepository = () -> List.of(electricVehicle);
        RentalRepository rentalRepository = new InMemoryRentalRepository();

        RentalService rentalService = createRentalService(vehicleRepository, rentalRepository);

        Customer customer = new Customer(1, "Lina Ahmad", "0599000000", 25);

        Rental rental = rentalService.rentVehicle(
                1,
                customer,
                1,
                LocalDate.now(),
                LocalDate.now().plusDays(2)
        );

        assertNotNull(rental);
    }
}