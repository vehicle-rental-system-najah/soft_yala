package com.vehiclerental.model;

/**
 * Represents a van vehicle type.
 */
public class Van extends Vehicle {

    /**
     * The number of seats in the van.
     */
    private int seats;

    /**
     * Creates a van.
     *
     * @param id the van id
     * @param plateNumber the van plate number
     * @param brand the van brand
     * @param model the van model
     * @param dailyRate the daily rental rate
     * @param seats the number of seats
     */
    public Van(int id, String plateNumber, String brand, String model,
               double dailyRate, int seats) {
        super(id, plateNumber, brand, model, dailyRate);
        this.seats = seats;
    }

    /**
     * Returns the number of seats.
     *
     * @return the number of seats
     */
    public int getSeats() {
        return seats;
    }

    /**
     * Returns the vehicle type.
     *
     * @return the vehicle type
     */
    @Override
    public String getType() {
        return "Van";
    }
}