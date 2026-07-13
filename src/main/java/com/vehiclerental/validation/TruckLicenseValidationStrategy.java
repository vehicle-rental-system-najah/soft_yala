package com.vehiclerental.validation;

import com.vehiclerental.model.Customer;
import com.vehiclerental.model.License;
import com.vehiclerental.model.LicenseType;
import com.vehiclerental.model.Truck;
import com.vehiclerental.model.Vehicle;
import com.vehiclerental.repository.RentalRepository;

import java.time.LocalDate;

/**
 * Validates truck rental rules.
 */
public class TruckLicenseValidationStrategy implements RentalValidationStrategy {

    /**
     * Checks that a customer has a truck license before renting a truck.
     *
     * @param customer the customer who wants to rent the vehicle
     * @param vehicle the selected vehicle
     * @param startDate the rental start date
     * @param endDate the rental end date
     * @param rentalRepository the repository that stores rental records
     * @return true if the truck license rule is satisfied, false otherwise
     */
    @Override
    public boolean isValid(Customer customer, Vehicle vehicle, LocalDate startDate,
                           LocalDate endDate, RentalRepository rentalRepository) {
        if (!(vehicle instanceof Truck)) {
            return true;
        }

        if (customer == null) {
            return false;
        }

        License license = customer.getLicense();

        if (license == null) {
            return false;
        }

        return license.getType() == LicenseType.TRUCK;
    }

    /**
     * Returns the truck license validation error message.
     *
     * @return the error message
     */
    @Override
    public String getErrorMessage() {
        return "Truck rental requires a truck license";
    }
}