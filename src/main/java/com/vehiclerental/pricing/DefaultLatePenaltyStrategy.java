package com.vehiclerental.pricing;

import com.vehiclerental.model.Rental;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DefaultLatePenaltyStrategy implements LatePenaltyStrategy {
    private static final double PENALTY_PERCENT = 0.25;

    @Override
    public double calculatePenalty(Rental rental, LocalDate returnDate) {
        if (!returnDate.isAfter(rental.getEndDate())) {
            return 0;
        }

        long lateDays = ChronoUnit.DAYS.between(rental.getEndDate(), returnDate);
        return lateDays * rental.getVehicle().getDailyRate() * PENALTY_PERCENT;
    }
}