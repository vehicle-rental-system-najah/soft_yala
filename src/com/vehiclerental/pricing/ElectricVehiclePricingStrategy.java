package com.vehiclerental.pricing;

import com.vehiclerental.model.Rental;

import java.time.temporal.ChronoUnit;

public class ElectricVehiclePricingStrategy implements PricingStrategy {
    private static final double ELECTRIC_DISCOUNT = 0.10;

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