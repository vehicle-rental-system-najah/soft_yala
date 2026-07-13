package com.vehiclerental.service;

import com.vehiclerental.model.Vehicle;
import com.vehiclerental.repository.InMemoryVehicleRepository;
import com.vehiclerental.repository.VehicleRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VehicleCatalogServiceTest {

    @Test
    void getAvailableVehiclesShouldHideRentedVehicles() {
        VehicleRepository vehicleRepository = new InMemoryVehicleRepository();
        VehicleCatalogService catalogService = new VehicleCatalogService(vehicleRepository);

        List<Vehicle> availableVehicles = catalogService.getAvailableVehicles();

        assertFalse(availableVehicles.isEmpty());

        for (Vehicle vehicle : availableVehicles) {
            assertTrue(vehicle.isAvailable());
        }
    }

    @Test
    void rentedVehicleShouldNotAppearInAvailableList() {
        VehicleRepository vehicleRepository = new InMemoryVehicleRepository();
        VehicleCatalogService catalogService = new VehicleCatalogService(vehicleRepository);

        List<Vehicle> availableVehicles = catalogService.getAvailableVehicles();

        boolean containsRentedCar = availableVehicles.stream()
                .anyMatch(vehicle -> vehicle.getPlateNumber().equals("PAL-200"));

        assertFalse(containsRentedCar);
    }
}