package com.vehiclerental.validation;

import com.vehiclerental.model.Customer;
import com.vehiclerental.model.Vehicle;
import com.vehiclerental.repository.RentalRepository;

import java.time.LocalDate;

/**
 * Validates that a vehicle is available before rental.
 */
public class AvailabilityValidationStrategy implements RentalValidationStrategy {

    /**
     * Checks that the selected vehicle exists, is available, and has no active rental.
     *
     * @param customer the customer who wants to rent the vehicle
     * @param vehicle the selected vehicle
     * @param startDate the rental start date
     * @param endDate the rental end date
     * @param rentalRepository the repository that stores rental records
     * @return true if the vehicle is available, false otherwise
     */
    @Override
    public boolean isValid(Customer customer, Vehicle vehicle, LocalDate startDate,
                           LocalDate endDate, RentalRepository rentalRepository) {
        if (vehicle == null) {
            return false;
        }

        if (!vehicle.isAvailable()) {
            return false;
        }

        return rentalRepository.findActiveRentalByVehicleId(vehicle.getId()) == null;
    }

    /**
     * Returns the availability validation error message.
     *
     * @return the error message
     */
    @Override
    public String getErrorMessage() {
        return "Vehicle is not available";
    }
}