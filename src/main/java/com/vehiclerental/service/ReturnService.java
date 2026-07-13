package com.vehiclerental.service;

import com.vehiclerental.model.Invoice;
import com.vehiclerental.model.Rental;
import com.vehiclerental.model.VehicleStatus;
import com.vehiclerental.repository.RentalRepository;

import java.time.LocalDate;

public class ReturnService {
    private RentalRepository rentalRepository;
    private BillingService billingService;

    public ReturnService(RentalRepository rentalRepository, BillingService billingService) {
        this.rentalRepository = rentalRepository;
        this.billingService = billingService;
    }

    public Invoice returnVehicle(int rentalId, LocalDate returnDate) {
        Rental rental = rentalRepository.findById(rentalId);

        if (rental == null) {
            System.out.println("Rental not found");
            return null;
        }

        if (!rental.isActive()) {
            System.out.println("Rental is already closed");
            return null;
        }

        Invoice invoice = billingService.createInvoice(rentalId, rental, returnDate);

        rental.close();
        rental.getVehicle().setStatus(VehicleStatus.AVAILABLE);

        return invoice;
    }
}