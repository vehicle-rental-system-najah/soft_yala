package com.vehiclerental.repository;

import com.vehiclerental.model.Car;
import com.vehiclerental.model.ElectricVehicle;
import com.vehiclerental.model.Motorcycle;
import com.vehiclerental.model.Truck;
import com.vehiclerental.model.Van;
import com.vehiclerental.model.Vehicle;
import com.vehiclerental.model.VehicleStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores vehicle data in memory.
 */
public class InMemoryVehicleRepository implements VehicleRepository {

    /**
     * List of vehicles stored in memory.
     */
    private List<Vehicle> vehicles;

    /**
     * Creates an in-memory vehicle repository with sample vehicles.
     */
    public InMemoryVehicleRepository() {
        vehicles = new ArrayList<>();

        Vehicle car1 = new Car(1, "PAL-100", "Toyota", "Corolla", 35.0);
        Vehicle car2 = new Car(2, "PAL-200", "Hyundai", "Elantra", 40.0);
        Vehicle car3 = new Car(3, "PAL-300", "Kia", "Picanto", 25.0);

        Vehicle truck1 = new Truck(4, "PAL-400", "Volvo", "FH", 90.0, 5000);
        Vehicle motorcycle1 = new Motorcycle(5, "PAL-500", "Honda", "CBR", 20.0, 150);
        Vehicle electric1 = new ElectricVehicle(6, "PAL-600", "Tesla", "Model 3", 80.0, 75, true);
        Vehicle van1 = new Van(7, "PAL-700", "Ford", "Transit", 60.0, 8);

        car2.setStatus(VehicleStatus.RENTED);

        vehicles.add(car1);
        vehicles.add(car2);
        vehicles.add(car3);
        vehicles.add(truck1);
        vehicles.add(motorcycle1);
        vehicles.add(electric1);
        vehicles.add(van1);
    }

    /**
     * Returns all vehicles stored in memory.
     *
     * @return a list of all vehicles
     */
    @Override
    public List<Vehicle> findAll() {
        return vehicles;
    }
}