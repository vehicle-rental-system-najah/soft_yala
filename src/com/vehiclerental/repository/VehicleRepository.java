package com.vehiclerental.repository;

import com.vehiclerental.model.Vehicle;
import java.util.List;

public interface VehicleRepository {
    List<Vehicle> findAll();
}