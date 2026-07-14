package com.vehiclerental.validation;

import com.vehiclerental.model.Customer;
import com.vehiclerental.model.Motorcycle;
import com.vehiclerental.model.Vehicle;
import com.vehiclerental.repository.RentalRepository;

import java.time.LocalDate;

/**
 * Validates motorcycle rental rules.
 */
public class MotorcycleAgeValidationStrategy implements RentalValidationStrategy {

    /**
     * The minimum age required to rent a motorcycle.
     */
    private static final int MIN_AGE = 18;

    /**
     * Checks that a customer is old enough before renting a motorcycle.
     *
     * @param customer the customer who wants to rent the vehicle
     * @param vehicle the selected vehicle
     * @param startDate the rental start date
     * @param endDate the rental end date
     * @param rentalRepository the repository that stores rental records
     * @return true if the age rule is satisfied, false otherwise
     */
    @Override
    public boolean isValid(Customer customer, Vehicle vehicle, LocalDate startDate,
                           LocalDate endDate, RentalRepository rentalRepository) {
        if (!(vehicle instanceof Motorcycle)) {
            return true;
        }

        return customer != null && customer.getAge() >= MIN_AGE;
    }

    /**
     * Returns the motorcycle age validation error message.
     *
     * @return the error message
     */
    @Override
    public String getErrorMessage() {
        return "Customer age is not allowed for motorcycle rental";
    }
}