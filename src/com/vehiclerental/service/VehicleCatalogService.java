package com.vehiclerental.service;

import com.vehiclerental.model.Vehicle;
import com.vehiclerental.repository.VehicleRepository;

import java.util.ArrayList;
import java.util.List;

public class VehicleCatalogService {
    private VehicleRepository vehicleRepository;

    public VehicleCatalogService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

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