package com.vehiclerental.validation;

import com.vehiclerental.model.Customer;
import com.vehiclerental.model.Vehicle;
import com.vehiclerental.repository.RentalRepository;

import java.time.LocalDate;

/**
 * Defines a validation rule that is checked before creating a rental.
 */
public interface RentalValidationStrategy {

    /**
     * Checks whether a rental request is valid.
     *
     * @param customer the customer who wants to rent the vehicle
     * @param vehicle the selected vehicle
     * @param startDate the rental start date
     * @param endDate the rental end date
     * @param rentalRepository the repository that stores rental records
     * @return true if the rental request is valid, false otherwise
     */
    boolean isValid(Customer customer, Vehicle vehicle, LocalDate startDate,
                    LocalDate endDate, RentalRepository rentalRepository);

    /**
     * Returns the error message used when validation fails.
     *
     * @return the validation error message
     */
    String getErrorMessage();
}