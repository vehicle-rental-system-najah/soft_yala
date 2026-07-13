package com.vehiclerental.model;

/**
 * Represents a motorcycle vehicle type.
 */
public class Motorcycle extends Vehicle {

    /**
     * The motorcycle engine capacity.
     */
    private int engineCapacity;

    /**
     * Creates a motorcycle.
     *
     * @param id the motorcycle id
     * @param plateNumber the motorcycle plate number
     * @param brand the motorcycle brand
     * @param model the motorcycle model
     * @param dailyRate the daily rental rate
     * @param engineCapacity the engine capacity
     */
    public Motorcycle(int id, String plateNumber, String brand, String model,
                      double dailyRate, int engineCapacity) {
        super(id, plateNumber, brand, model, dailyRate);
        this.engineCapacity = engineCapacity;
    }

    /**
     * Returns the engine capacity.
     *
     * @return the engine capacity
     */
    public int getEngineCapacity() {
        return engineCapacity;
    }

    /**
     * Returns the vehicle type.
     *
     * @return the vehicle type
     */
    @Override
    public String getType() {
        return "Motorcycle";
    }
}