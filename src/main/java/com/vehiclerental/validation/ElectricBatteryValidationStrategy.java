package com.vehiclerental.validation;

import com.vehiclerental.model.Customer;
import com.vehiclerental.model.ElectricVehicle;
import com.vehiclerental.model.Vehicle;
import com.vehiclerental.repository.RentalRepository;

import java.time.LocalDate;

/**
 * Validates electric vehicle rental rules.
 */
public class ElectricBatteryValidationStrategy implements RentalValidationStrategy {

    /**
     * The minimum accepted battery level for electric vehicle rental.
     */
    private static final int MIN_BATTERY_LEVEL = 30;

    /**
     * Checks that an electric vehicle battery was checked and has enough charge.
     *
     * @param customer the customer who wants to rent the vehicle
     * @param vehicle the selected vehicle
     * @param startDate the rental start date
     * @param endDate the rental end date
     * @param rentalRepository the repository that stores rental records
     * @return true if the battery rule is satisfied, false otherwise
     */
    @Override
    public boolean isValid(Customer customer, Vehicle vehicle, LocalDate startDate,
                           LocalDate endDate, RentalRepository rentalRepository) {
        if (!(vehicle instanceof ElectricVehicle)) {
            return true;
        }

        ElectricVehicle electricVehicle = (ElectricVehicle) vehicle;

        return electricVehicle.isBatteryChecked()
                && electricVehicle.getBatteryLevel() >= MIN_BATTERY_LEVEL;
    }

    /**
     * Returns the electric vehicle battery validation error message.
     *
     * @return the error message
     */
    @Override
    public String getErrorMessage() {
        return "Electric vehicle battery check failed";
    }
}