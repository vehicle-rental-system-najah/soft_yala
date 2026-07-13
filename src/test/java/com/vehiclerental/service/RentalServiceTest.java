package com.vehiclerental.service;

import com.vehiclerental.model.Customer;
import com.vehiclerental.model.Rental;
import com.vehiclerental.model.VehicleStatus;
import com.vehiclerental.repository.InMemoryRentalRepository;
import com.vehiclerental.repository.InMemoryVehicleRepository;
import com.vehiclerental.repository.RentalRepository;
import com.vehiclerental.repository.VehicleRepository;
import com.vehiclerental.validation.AvailabilityValidationStrategy;
import com.vehiclerental.validation.DurationValidationStrategy;
import com.vehiclerental.validation.RentalValidationStrategy;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RentalServiceTest {

    private RentalService createRentalService(VehicleRepository vehicleRepository,
                                              RentalRepository rentalRepository) {
        List<RentalValidationStrategy> validations = new ArrayList<>();
        validations.add(new AvailabilityValidationStrategy());
        validations.add(new DurationValidationStrategy());

        return new RentalService(vehicleRepository, rentalRepository, validations);
    }

    @Test
    void rentVehicleShouldCreateRentalAndChangeVehicleStatus() {
        VehicleRepository vehicleRepository = new InMemoryVehicleRepository();
        RentalRepository rentalRepository = new InMemoryRentalRepository();
        RentalService rentalService = createRentalService(vehicleRepository, rentalRepository);

        Customer customer = new Customer(1, "Lina Ahmad", "0599000000", 25);

        Rental rental = rentalService.rentVehicle(
                1,
                customer,
                1,
                LocalDate.now(),
                LocalDate.now().plusDays(3)
        );

        assertNotNull(rental);
        assertEquals(1, rental.getId());
        assertEquals(VehicleStatus.RENTED, rental.getVehicle().getStatus());
        assertTrue(rental.isActive());
    }

    @Test
    void rentVehicleShouldRejectDoubleBooking() {
        VehicleRepository vehicleRepository = new InMemoryVehicleRepository();
        RentalRepository rentalRepository = new InMemoryRentalRepository();
        RentalService rentalService = createRentalService(vehicleRepository, rentalRepository);

        Customer customer = new Customer(1, "Lina Ahmad", "0599000000", 25);

        Rental firstRental = rentalService.rentVehicle(
                1,
                customer,
                1,
                LocalDate.now(),
                LocalDate.now().plusDays(3)
        );

        Rental secondRental = rentalService.rentVehicle(
                2,
                customer,
                1,
                LocalDate.now(),
                LocalDate.now().plusDays(2)
        );

        assertNotNull(firstRental);
        assertNull(secondRental);
    }

    @Test
    void rentVehicleShouldRejectInvalidRentalPeriod() {
        VehicleRepository vehicleRepository = new InMemoryVehicleRepository();
        RentalRepository rentalRepository = new InMemoryRentalRepository();
        RentalService rentalService = createRentalService(vehicleRepository, rentalRepository);

        Customer customer = new Customer(1, "Lina Ahmad", "0599000000", 25);

        Rental rental = rentalService.rentVehicle(
                1,
                customer,
                1,
                LocalDate.now(),
                LocalDate.now().minusDays(1)
        );

        assertNull(rental);
    }

    @Test
    void rentVehicleShouldRejectUnavailableVehicle() {
        VehicleRepository vehicleRepository = new InMemoryVehicleRepository();
        RentalRepository rentalRepository = new InMemoryRentalRepository();
        RentalService rentalService = createRentalService(vehicleRepository, rentalRepository);

        Customer customer = new Customer(1, "Lina Ahmad", "0599000000", 25);

        Rental rental = rentalService.rentVehicle(
                1,
                customer,
                2,
                LocalDate.now(),
                LocalDate.now().plusDays(2)
        );

        assertNull(rental);
    }
}