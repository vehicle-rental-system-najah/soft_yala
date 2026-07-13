package com.vehiclerental.pricing;

import com.vehiclerental.model.Rental;

import java.time.temporal.ChronoUnit;

/**
 * Calculates rental cost for car rentals.
 */
public class CarPricingStrategy implements PricingStrategy {

    /**
     * Calculates car rental cost based on rental days and daily rate.
     *
     * @param rental the rental record
     * @return the calculated car rental cost
     */
    @Override
    public double calculateRentalCost(Rental rental) {
        long days = ChronoUnit.DAYS.between(rental.getStartDate(), rental.getEndDate());

        if (days < 1) {
            days = 1;
        }

        return days * rental.getVehicle().getDailyRate();
    }
}