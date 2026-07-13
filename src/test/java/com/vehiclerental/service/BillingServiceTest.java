package com.vehiclerental.service;

import com.vehiclerental.model.Car;
import com.vehiclerental.model.Customer;
import com.vehiclerental.model.Invoice;
import com.vehiclerental.model.Rental;
import com.vehiclerental.pricing.DefaultLatePenaltyStrategy;
import com.vehiclerental.pricing.DefaultPricingStrategy;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BillingServiceTest {

    @Test
    void createInvoiceShouldCalculateRentalCost() {
        BillingService billingService = new BillingService(
                new DefaultPricingStrategy(),
                new DefaultLatePenaltyStrategy()
        );

        Customer customer = new Customer(1, "Lina Ahmad", "0599000000", 25);
        Car car = new Car(1, "PAL-100", "Toyota", "Corolla", 35.0);

        Rental rental = new Rental(
                1,
                customer,
                car,
                LocalDate.of(2026, 7, 13),
                LocalDate.of(2026, 7, 16)
        );

        Invoice invoice = billingService.createInvoice(
                1,
                rental,
                LocalDate.of(2026, 7, 16)
        );

        assertEquals(105.0, invoice.getRentalCost(), 0.001);
        assertEquals(0.0, invoice.getLatePenalty(), 0.001);
        assertEquals(105.0, invoice.getTotalAmount(), 0.001);
    }

    @Test
    void createInvoiceShouldCalculateLatePenalty() {
        BillingService billingService = new BillingService(
                new DefaultPricingStrategy(),
                new DefaultLatePenaltyStrategy()
        );

        Customer customer = new Customer(1, "Lina Ahmad", "0599000000", 25);
        Car car = new Car(1, "PAL-100", "Toyota", "Corolla", 40.0);

        Rental rental = new Rental(
                1,
                customer,
                car,
                LocalDate.of(2026, 7, 13),
                LocalDate.of(2026, 7, 15)
        );

        Invoice invoice = billingService.createInvoice(
                1,
                rental,
                LocalDate.of(2026, 7, 17)
        );

        assertEquals(80.0, invoice.getRentalCost(), 0.001);
        assertEquals(20.0, invoice.getLatePenalty(), 0.001);
        assertEquals(100.0, invoice.getTotalAmount(), 0.001);
    }
}