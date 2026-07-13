package com.vehiclerental.service;

import com.vehiclerental.model.Vehicle;
import com.vehiclerental.repository.VehicleRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides vehicle catalog operations.
 */
public class VehicleCatalogService {

    /**
     * Repository used to access vehicle data.
     */
    private VehicleRepository vehicleRepository;

    /**
     * Creates a vehicle catalog service.
     *
     * @param vehicleRepository the repository that stores vehicle data
     */
    public VehicleCatalogService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    /**
     * Returns only the vehicles that are currently available.
     *
     * @return a list of available vehicles
     */
    public List<Vehicle> getAvailableVehicles() {
        List<Vehicle> availableVehicles = new ArrayList<>();

        for (Vehicle vehicle : vehicleRepository.findAll()) {
            if (vehicle.isAvailable()) {
                availableVehicles.add(vehicle);
            }
        }

        return availableVehicles;
    }
}