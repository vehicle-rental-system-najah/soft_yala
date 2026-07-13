package com.vehiclerental.pricing;

import com.vehiclerental.model.Rental;

import java.time.temporal.ChronoUnit;

/**
 * Calculates rental cost using the default daily rate rule.
 */
public class DefaultPricingStrategy implements PricingStrategy {

    /**
     * Calculates the rental cost based on rental days and vehicle daily rate.
     *
     * @param rental the rental record
     * @return the calculated rental cost
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