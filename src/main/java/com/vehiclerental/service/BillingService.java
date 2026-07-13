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

public class BillingService {
    private PricingStrategy pricingStrategy;
    private LatePenaltyStrategy latePenaltyStrategy;

    public BillingService(PricingStrategy pricingStrategy, LatePenaltyStrategy latePenaltyStrategy) {
        this.pricingStrategy = pricingStrategy;
        this.latePenaltyStrategy = latePenaltyStrategy;
    }

    public Invoice createInvoice(int invoiceId, Rental rental, LocalDate returnDate) {
        PricingStrategy selectedPricingStrategy = selectPricingStrategy(rental.getVehicle());

        double rentalCost = selectedPricingStrategy.calculateRentalCost(rental);
        double latePenalty = latePenaltyStrategy.calculatePenalty(rental, returnDate);

        return new Invoice(invoiceId, rental, returnDate, rentalCost, latePenalty);
    }

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