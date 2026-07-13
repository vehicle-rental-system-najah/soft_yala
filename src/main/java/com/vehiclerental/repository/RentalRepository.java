package com.vehiclerental.repository;

import com.vehiclerental.model.Rental;
import java.util.List;

public interface RentalRepository {
    void save(Rental rental);

    Rental findById(int id);

    Rental findActiveRentalByVehicleId(int vehicleId);

    List<Rental> findAll();
}