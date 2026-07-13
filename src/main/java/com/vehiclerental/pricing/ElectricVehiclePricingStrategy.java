package com.vehiclerental.pricing;

import com.vehiclerental.model.Rental;

import java.time.temporal.ChronoUnit;

/**
 * Calculates rental cost for electric vehicle rentals.
 */
public class ElectricVehiclePricingStrategy implements PricingStrategy {

    /**
     * Discount percentage applied to electric vehicle rentals.
     */
    private static final double ELECTRIC_DISCOUNT = 0.10;

    /**
     * Calculates electric vehicle rental cost after applying a discount.
     *
     * @param rental the rental record
     * @return the calculated electric vehicle rental cost
     */
    @Override
    public double calculateRentalCost(Rental rental) {
        long days = ChronoUnit.DAYS.between(rental.getStartDate(), rental.getEndDate());

        if (days < 1) {
            days = 1;
        }

        double baseCost = days * rental.getVehicle().getDailyRate();
        return baseCost - (baseCost * ELECTRIC_DISCOUNT);
    }
}