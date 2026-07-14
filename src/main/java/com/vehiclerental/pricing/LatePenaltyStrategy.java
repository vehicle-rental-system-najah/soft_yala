package com.vehiclerental.pricing;

import com.vehiclerental.model.Rental;

import java.time.LocalDate;

/**
 * Defines a rule for calculating late return penalties.
 */
public interface LatePenaltyStrategy {

    /**
     * Calculates the late return penalty.
     *
     * @param rental the rental record
     * @param returnDate the actual return date
     * @return the calculated late penalty
     */
    double calculatePenalty(Rental rental, LocalDate returnDate);
}