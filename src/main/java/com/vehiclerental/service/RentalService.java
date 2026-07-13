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

/**
 * Handles vehicle rental operations.
 */
public class RentalService {

    /**
     * Repository used to access vehicle data.
     */
    private VehicleRepository vehicleRepository;

    /**
     * Repository used to save and search rental records.
     */
    private RentalRepository rentalRepository;

    /**
     * List of validation rules used before creating a rental.
     */
    private List<RentalValidationStrategy> validations;

    /**
     * Creates a rental service.
     *
     * @param vehicleRepository the repository that stores vehicles
     * @param rentalRepository the repository that stores rentals
     * @param validations the validation rules used before renting a vehicle
     */
    public RentalService(VehicleRepository vehicleRepository,
                         RentalRepository rentalRepository,
                         List<RentalValidationStrategy> validations) {
        this.vehicleRepository = vehicleRepository;
        this.rentalRepository = rentalRepository;
        this.validations = validations;
    }

    /**
     * Creates a rental if the vehicle and rental period are valid.
     *
     * @param rentalId the rental id
     * @param customer the customer who rents the vehicle
     * @param vehicleId the id of the selected vehicle
     * @param startDate the rental start date
     * @param endDate the rental end date
     * @return the created rental, or null if the rental is rejected
     */
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

    /**
     * Searches for a vehicle by id.
     *
     * @param vehicleId the vehicle id
     * @return the matching vehicle, or null if it is not found
     */
    private Vehicle findVehicleById(int vehicleId) {
        for (Vehicle vehicle : vehicleRepository.findAll()) {
            if (vehicle.getId() == vehicleId) {
                return vehicle;
            }
        }
        return null;
    }
}