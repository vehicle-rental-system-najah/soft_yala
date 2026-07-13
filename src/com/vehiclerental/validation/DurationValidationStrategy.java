package com.vehiclerental.validation;

import com.vehiclerental.model.Customer;
import com.vehiclerental.model.Vehicle;
import com.vehiclerental.repository.RentalRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DurationValidationStrategy implements RentalValidationStrategy {
    private static final int MAX_RENTAL_DAYS = 30;

    @Override
    public boolean isValid(Customer customer, Vehicle vehicle, LocalDate startDate,
                           LocalDate endDate, RentalRepository rentalRepository) {
        if (startDate == null || endDate == null) {
            return false;
        }

        if (!endDate.isAfter(startDate)) {
            return false;
        }

        long days = ChronoUnit.DAYS.between(startDate, endDate);
        return days <= MAX_RENTAL_DAYS;
    }

    @Override
    public String getErrorMessage() {
        return "Invalid rental period";
    }
}