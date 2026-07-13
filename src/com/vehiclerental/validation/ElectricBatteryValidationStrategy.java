package com.vehiclerental.validation;

import com.vehiclerental.model.Customer;
import com.vehiclerental.model.ElectricVehicle;
import com.vehiclerental.model.Vehicle;
import com.vehiclerental.repository.RentalRepository;

import java.time.LocalDate;

public class ElectricBatteryValidationStrategy implements RentalValidationStrategy {
    private static final int MIN_BATTERY_LEVEL = 30;

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

    @Override
    public String getErrorMessage() {
        return "Electric vehicle battery check failed";
    }
}