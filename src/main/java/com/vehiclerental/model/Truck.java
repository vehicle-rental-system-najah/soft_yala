package com.vehiclerental.model;

/**
 * Represents a truck vehicle type.
 */
public class Truck extends Vehicle {

    /**
     * The maximum load allowed for the truck in kilograms.
     */
    private double maxLoadKg;

    /**
     * Creates a truck.
     *
     * @param id the truck id
     * @param plateNumber the truck plate number
     * @param brand the truck brand
     * @param model the truck model
     * @param dailyRate the daily rental rate
     * @param maxLoadKg the maximum load in kilograms
     */
    public Truck(int id, String plateNumber, String brand, String model,
                 double dailyRate, double maxLoadKg) {
        super(id, plateNumber, brand, model, dailyRate);
        this.maxLoadKg = maxLoadKg;
    }

    /**
     * Returns the maximum truck load.
     *
     * @return the maximum load in kilograms
     */
    public double getMaxLoadKg() {
        return maxLoadKg;
    }

    /**
     * Returns the vehicle type.
     *
     * @return the vehicle type
     */
    @Override
    public String getType() {
        return "Truck";
    }
}