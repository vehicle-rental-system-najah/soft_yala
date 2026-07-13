package com.vehiclerental.pricing;

import com.vehiclerental.model.Rental;

import java.time.temporal.ChronoUnit;

public class TruckPricingStrategy implements PricingStrategy {
    private static final double TRUCK_EXTRA_PERCENT = 0.20;

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