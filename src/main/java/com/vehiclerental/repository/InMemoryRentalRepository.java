package com.vehiclerental.repository;

import com.vehiclerental.model.Rental;
import java.util.ArrayList;
import java.util.List;

/**
 * Stores rental records in memory.
 */
public class InMemoryRentalRepository implements RentalRepository {

    /**
     * List of rental records stored in memory.
     */
    private List<Rental> rentals;

    /**
     * Creates an empty in-memory rental repository.
     */
    public InMemoryRentalRepository() {
        rentals = new ArrayList<>();
    }

    /**
     * Saves a rental record.
     *
     * @param rental the rental to save
     */
    @Override
    public void save(Rental rental) {
        rentals.add(rental);
    }

    /**
     * Finds a rental by id.
     *
     * @param id the rental id
     * @return the matching rental, or null if not found
     */
    @Override
    public Rental findById(int id) {
        for (Rental rental : rentals) {
            if (rental.getId() == id) {
                return rental;
            }
        }
        return null;
    }

    /**
     * Finds an active rental by vehicle id.
     *
     * @param vehicleId the vehicle id
     * @return the active rental, or null if no active rental exists
     */
    @Override
    public Rental findActiveRentalByVehicleId(int vehicleId) {
        for (Rental rental : rentals) {
            if (rental.getVehicle().getId() == vehicleId && rental.isActive()) {
                return rental;
            }
        }
        return null;
    }

    /**
     * Returns all rental records.
     *
     * @return a list of rentals
     */
    @Override
    public List<Rental> findAll() {
        return rentals;
    }
}