package com.vehiclerental.validation;

import com.vehiclerental.model.Customer;
import com.vehiclerental.model.Vehicle;
import com.vehiclerental.repository.RentalRepository;

import java.time.LocalDate;

public interface RentalValidationStrategy {
    boolean isValid(Customer customer, Vehicle vehicle, LocalDate startDate,
                    LocalDate endDate, RentalRepository rentalRepository);

    String getErrorMessage();
}