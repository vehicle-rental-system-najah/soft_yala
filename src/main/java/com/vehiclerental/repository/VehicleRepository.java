package com.vehiclerental.repository;

import com.vehiclerental.model.Vehicle;
import java.util.List;

/**
 * Defines operations for accessing vehicle data.
 */
public interface VehicleRepository {

    /**
     * Returns all vehicles stored in the repository.
     *
     * @return a list of all vehicles
     */
    List<Vehicle> findAll();
}