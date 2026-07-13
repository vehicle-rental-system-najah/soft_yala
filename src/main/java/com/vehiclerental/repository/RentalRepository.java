package com.vehiclerental.repository;

import com.vehiclerental.model.Rental;
import java.util.List;

/**
 * Defines operations for accessing rental data.
 */
public interface RentalRepository {

    /**
     * Saves a rental record.
     *
     * @param rental the rental to save
     */
    void save(Rental rental);

    /**
     * Finds a rental by id.
     *
     * @param id the rental id
     * @return the matching rental, or null if not found
     */
    Rental findById(int id);

    /**
     * Finds the active rental for a specific vehicle.
     *
     * @param vehicleId the vehicle id
     * @return the active rental, or null if no active rental exists
     */
    Rental findActiveRentalByVehicleId(int vehicleId);

    /**
     * Returns all rental records.
     *
     * @return a list of rentals
     */
    List<Rental> findAll();
}