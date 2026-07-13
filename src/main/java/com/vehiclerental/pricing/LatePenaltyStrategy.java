package com.vehiclerental.pricing;

import com.vehiclerental.model.Rental;

import java.time.LocalDate;

public interface LatePenaltyStrategy {
    double calculatePenalty(Rental rental, LocalDate returnDate);
}