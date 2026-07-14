package com.vehiclerental.validation;

import com.vehiclerental.model.Customer;
import com.vehiclerental.model.Vehicle;
import com.vehiclerental.repository.RentalRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Validates the rental duration.
 */
public class DurationValidationStrategy implements RentalValidationStrategy {

    /**
     * The maximum allowed rental duration in days.
     */
    private static final int MAX_RENTAL_DAYS = 30;

    /**
     * Checks that the rental dates are valid and within the allowed duration.
     *
     * @param customer the customer who wants to rent the vehicle
     * @param vehicle the selected vehicle
     * @param startDate the rental start date
     * @param endDate the rental end date
     * @param rentalRepository the repository that stores rental records
     * @return true if the rental period is valid, false otherwise
     */
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

    /**
     * Returns the duration validation error message.
     *
     * @return the error message
     */
    @Override
    public String getErrorMessage() {
        return "Invalid rental period";
    }
}