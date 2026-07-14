package com.vehiclerental.service;

import com.vehiclerental.model.Car;
import com.vehiclerental.model.Customer;
import com.vehiclerental.model.Invoice;
import com.vehiclerental.model.Rental;
import com.vehiclerental.model.VehicleStatus;
import com.vehiclerental.pricing.DefaultLatePenaltyStrategy;
import com.vehiclerental.pricing.DefaultPricingStrategy;
import com.vehiclerental.repository.InMemoryRentalRepository;
import com.vehiclerental.repository.RentalRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class ReturnServiceTest {

    private ReturnService createReturnService(RentalRepository rentalRepository) {
        BillingService billingService = new BillingService(
                new DefaultPricingStrategy(),
                new DefaultLatePenaltyStrategy()
        );

        return new ReturnService(rentalRepository, billingService);
    }

    @Test
    void returnVehicleShouldCloseRentalAndMakeVehicleAvailable() {
        RentalRepository rentalRepository = new InMemoryRentalRepository();
        ReturnService returnService = createReturnService(rentalRepository);

        Customer customer = new Customer(1, "Lina Ahmad", "0599000000", 25);
        Car car = new Car(1, "PAL-100", "Toyota", "Corolla", 35.0);
        car.setStatus(VehicleStatus.RENTED);

        Rental rental = new Rental(
                1,
                customer,
                car,
                LocalDate.of(2026, Month.JULY, 13),
                LocalDate.of(2026, Month.JULY, 14)
        );

        rentalRepository.save(rental);

        Invoice invoice = returnService.returnVehicle(
                1,
                LocalDate.of(2026, Month.JULY, 14)
        );

        assertNotNull(invoice);
        assertFalse(rental.isActive());
        assertEquals(VehicleStatus.AVAILABLE, car.getStatus());
    }

    @Test
    void returnVehicleShouldReturnNullForUnknownRental() {
        RentalRepository rentalRepository = new InMemoryRentalRepository();
        ReturnService returnService = createReturnService(rentalRepository);

        Invoice invoice = returnService.returnVehicle(
                99,
                LocalDate.of(2026, Month.JULY, 14)
        );

        assertNull(invoice);
    }
}