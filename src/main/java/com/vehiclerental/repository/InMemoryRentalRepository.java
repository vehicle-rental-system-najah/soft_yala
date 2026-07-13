package com.vehiclerental.repository;

import com.vehiclerental.model.Rental;
import java.util.ArrayList;
import java.util.List;

public class InMemoryRentalRepository implements RentalRepository {
    private List<Rental> rentals;

    public InMemoryRentalRepository() {
        rentals = new ArrayList<>();
    }

    @Override
    public void save(Rental rental) {
        rentals.add(rental);
    }

    @Override
    public Rental findById(int id) {
        for (Rental rental : rentals) {
            if (rental.getId() == id) {
                return rental;
            }
        }
        return null;
    }

    @Override
    public Rental findActiveRentalByVehicleId(int vehicleId) {
        for (Rental rental : rentals) {
            if (rental.getVehicle().getId() == vehicleId && rental.isActive()) {
                return rental;
            }
        }
        return null;
    }

    @Override
    public List<Rental> findAll() {
        return rentals;
    }
}