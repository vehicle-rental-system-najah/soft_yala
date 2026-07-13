package com.vehiclerental.validation;

import com.vehiclerental.model.Customer;
import com.vehiclerental.model.Motorcycle;
import com.vehiclerental.model.Vehicle;
import com.vehiclerental.repository.RentalRepository;

import java.time.LocalDate;

public class MotorcycleAgeValidationStrategy implements RentalValidationStrategy {
    private static final int MIN_AGE = 18;

    @Override
    public boolean isValid(Customer customer, Vehicle vehicle, LocalDate startDate,
                           LocalDate endDate, RentalRepository rentalRepository) {
        if (!(vehicle instanceof Motorcycle)) {
            return true;
        }

        return customer != null && customer.getAge() >= MIN_AGE;
    }

    @Override
    public String getErrorMessage() {
        return "Customer age is not allowed for motorcycle rental";
    }
}