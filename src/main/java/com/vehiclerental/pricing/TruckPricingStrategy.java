package com.vehiclerental.pricing;

import com.vehiclerental.model.Rental;

import java.time.temporal.ChronoUnit;

/**
 * Calculates rental cost for truck rentals.
 */
public class TruckPricingStrategy implements PricingStrategy {

    /**
     * Extra percentage added to truck rental cost.
     */
    private static final double TRUCK_EXTRA_PERCENT = 0.20;

    /**
     * Calculates truck rental cost with an extra percentage.
     *
     * @param rental the rental record
     * @return the calculated truck rental cost
     */
    @Override
    public double calculateRentalCost(Rental rental) {
        long days = ChronoUnit.DAYS.between(rental.getStartDate(), rental.getEndDate());

        if (days < 1) {
            days = 1;
        }

        double baseCost = days * rental.getVehicle().getDailyRate();
        return baseCost + (baseCost * TRUCK_EXTRA_PERCENT);
    }
}