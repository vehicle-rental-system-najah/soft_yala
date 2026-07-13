package com.vehiclerental.validation;

import com.vehiclerental.model.Customer;
import com.vehiclerental.model.Vehicle;
import com.vehiclerental.repository.RentalRepository;

import java.time.LocalDate;

public class AvailabilityValidationStrategy implements RentalValidationStrategy {

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

    @Override
    public String getErrorMessage() {
        return "Vehicle is not available";
    }
}