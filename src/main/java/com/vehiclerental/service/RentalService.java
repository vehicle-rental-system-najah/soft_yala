package com.vehiclerental.service;

import com.vehiclerental.model.Customer;
import com.vehiclerental.model.Rental;
import com.vehiclerental.model.Vehicle;
import com.vehiclerental.model.VehicleStatus;
import com.vehiclerental.repository.RentalRepository;
import com.vehiclerental.repository.VehicleRepository;
import com.vehiclerental.validation.RentalValidationStrategy;

import java.time.LocalDate;
import java.util.List;

public class RentalService {
    private VehicleRepository vehicleRepository;
    private RentalRepository rentalRepository;
    private List<RentalValidationStrategy> validations;

    public RentalService(VehicleRepository vehicleRepository,
                         RentalRepository rentalRepository,
                         List<RentalValidationStrategy> validations) {
        this.vehicleRepository = vehicleRepository;
        this.rentalRepository = rentalRepository;
        this.validations = validations;
    }

    public Rental rentVehicle(int rentalId, Customer customer, int vehicleId,
                              LocalDate startDate, LocalDate endDate) {
        Vehicle vehicle = findVehicleById(vehicleId);

        if (vehicle == null) {
            System.out.println("Vehicle not found");
            return null;
        }

        for (RentalValidationStrategy validation : validations) {
            if (!validation.isValid(customer, vehicle, startDate, endDate, rentalRepository)) {
                System.out.println(validation.getErrorMessage());
                return null;
            }
        }

        Rental rental = new Rental(rentalId, customer, vehicle, startDate, endDate);
        rentalRepository.save(rental);
        vehicle.setStatus(VehicleStatus.RENTED);

        return rental;
    }

    private Vehicle findVehicleById(int vehicleId) {
        for (Vehicle vehicle : vehicleRepository.findAll()) {
            if (vehicle.getId() == vehicleId) {
                return vehicle;
            }
        }
        return null;
    }
}