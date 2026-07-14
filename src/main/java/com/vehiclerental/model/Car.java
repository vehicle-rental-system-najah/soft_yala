package com.vehiclerental.model;

/**
 * Represents a car vehicle type.
 */
public class Car extends Vehicle {

    /**
     * Creates a car.
     *
     * @param id the car id
     * @param plateNumber the car plate number
     * @param brand the car brand
     * @param model the car model
     * @param dailyRate the daily rental rate
     */
    public Car(int id, String plateNumber, String brand, String model, double dailyRate) {
        super(id, plateNumber, brand, model, dailyRate);
    }

    /**
     * Returns the vehicle type.
     *
     * @return the vehicle type
     */
    @Override
    public String getType() {
        return "Car";
    }
}