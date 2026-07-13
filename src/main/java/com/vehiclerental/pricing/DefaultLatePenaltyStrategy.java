package com.vehiclerental.pricing;

import com.vehiclerental.model.Rental;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Calculates late return penalties using a fixed percentage of the daily rate.
 */
public class DefaultLatePenaltyStrategy implements LatePenaltyStrategy {

    /**
     * Penalty percentage applied for each late day.
     */
    private static final double PENALTY_PERCENT = 0.25;

    /**
     * Calculates the late return penalty.
     *
     * @param rental the rental record
     * @param returnDate the actual return date
     * @return the calculated late penalty
     */
    @Override
    public double calculatePenalty(Rental rental, LocalDate returnDate) {
        if (!returnDate.isAfter(rental.getEndDate())) {
            return 0;
        }

        long lateDays = ChronoUnit.DAYS.between(rental.getEndDate(), returnDate);
        return lateDays * rental.getVehicle().getDailyRate() * PENALTY_PERCENT;
    }
}