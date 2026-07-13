package com.vehiclerental.validation;

import com.vehiclerental.model.Customer;
import com.vehiclerental.model.License;
import com.vehiclerental.model.LicenseType;
import com.vehiclerental.model.Truck;
import com.vehiclerental.model.Vehicle;
import com.vehiclerental.repository.RentalRepository;

import java.time.LocalDate;

public class TruckLicenseValidationStrategy implements RentalValidationStrategy {

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

    @Override
    public String getErrorMessage() {
        return "Truck rental requires a truck license";
    }
}