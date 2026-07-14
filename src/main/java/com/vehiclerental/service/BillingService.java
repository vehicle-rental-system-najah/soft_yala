package com.vehiclerental.service;

import com.vehiclerental.model.Car;
import com.vehiclerental.model.ElectricVehicle;
import com.vehiclerental.model.Invoice;
import com.vehiclerental.model.Rental;
import com.vehiclerental.model.Truck;
import com.vehiclerental.model.Vehicle;
import com.vehiclerental.pricing.CarPricingStrategy;
import com.vehiclerental.pricing.ElectricVehiclePricingStrategy;
import com.vehiclerental.pricing.LatePenaltyStrategy;
import com.vehiclerental.pricing.PricingStrategy;
import com.vehiclerental.pricing.TruckPricingStrategy;

import java.time.LocalDate;

/**
 * Handles billing and invoice creation for rental records.
 */
public class BillingService {

    /**
     * Default pricing strategy used when no specific vehicle strategy is selected.
     */
    private PricingStrategy pricingStrategy;

    /**
     * Strategy used to calculate late return penalties.
     */
    private LatePenaltyStrategy latePenaltyStrategy;

    /**
     * Creates a billing service.
     *
     * @param pricingStrategy the default pricing strategy
     * @param latePenaltyStrategy the late penalty strategy
     */
    public BillingService(PricingStrategy pricingStrategy, LatePenaltyStrategy latePenaltyStrategy) {
        this.pricingStrategy = pricingStrategy;
        this.latePenaltyStrategy = latePenaltyStrategy;
    }

    /**
     * Creates an invoice for a rental based on the return date.
     *
     * @param invoiceId the invoice id
     * @param rental the rental record
     * @param returnDate the actual vehicle return date
     * @return the generated invoice
     */
    public Invoice createInvoice(int invoiceId, Rental rental, LocalDate returnDate) {
        PricingStrategy selectedPricingStrategy = selectPricingStrategy(rental.getVehicle());

        double rentalCost = selectedPricingStrategy.calculateRentalCost(rental);
        double latePenalty = latePenaltyStrategy.calculatePenalty(rental, returnDate);

        return new Invoice(invoiceId, rental, returnDate, rentalCost, latePenalty);
    }

    /**
     * Selects a pricing strategy depending on the vehicle type.
     *
     * @param vehicle the vehicle being rented
     * @return the selected pricing strategy
     */
    private PricingStrategy selectPricingStrategy(Vehicle vehicle) {
        if (vehicle instanceof Truck) {
            return new TruckPricingStrategy();
        }

        if (vehicle instanceof ElectricVehicle) {
            return new ElectricVehiclePricingStrategy();
        }

        if (vehicle instanceof Car) {
            return new CarPricingStrategy();
        }

        return pricingStrategy;
    }
}