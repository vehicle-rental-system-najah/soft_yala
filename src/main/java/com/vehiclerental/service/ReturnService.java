package com.vehiclerental.service;

import com.vehiclerental.model.Invoice;
import com.vehiclerental.model.Rental;
import com.vehiclerental.model.VehicleStatus;
import com.vehiclerental.repository.RentalRepository;

import java.time.LocalDate;

/**
 * Handles vehicle return operations.
 */
public class ReturnService {

    /**
     * Repository used to search rental records.
     */
    private RentalRepository rentalRepository;

    /**
     * Service used to create invoices.
     */
    private BillingService billingService;

    /**
     * Creates a return service.
     *
     * @param rentalRepository the repository that stores rentals
     * @param billingService the billing service used to create invoices
     */
    public ReturnService(RentalRepository rentalRepository, BillingService billingService) {
        this.rentalRepository = rentalRepository;
        this.billingService = billingService;
    }

    /**
     * Returns a rented vehicle and closes the rental record.
     *
     * @param rentalId the rental id
     * @param returnDate the actual return date
     * @return the generated invoice, or null if the return cannot be processed
     */
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