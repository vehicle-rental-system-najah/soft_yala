package com.vehiclerental.model;

/**
 * Represents a general vehicle in the rental system.
 */
public abstract class Vehicle {

    /**
     * The vehicle id.
     */
    private int id;

    /**
     * The vehicle plate number.
     */
    private String plateNumber;

    /**
     * The vehicle brand.
     */
    private String brand;

    /**
     * The vehicle model.
     */
    private String model;

    /**
     * The daily rental rate of the vehicle.
     */
    private double dailyRate;

    /**
     * The current vehicle status.
     */
    private VehicleStatus status;

    /**
     * Creates a vehicle with basic vehicle information.
     *
     * @param id the vehicle id
     * @param plateNumber the vehicle plate number
     * @param brand the vehicle brand
     * @param model the vehicle model
     * @param dailyRate the daily rental rate
     */
    public Vehicle(int id, String plateNumber, String brand, String model, double dailyRate) {
        this.id = id;
        this.plateNumber = plateNumber;
        this.brand = brand;
        this.model = model;
        this.dailyRate = dailyRate;
        this.status = VehicleStatus.AVAILABLE;
    }

    /**
     * Returns the vehicle id.
     *
     * @return the vehicle id
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the vehicle plate number.
     *
     * @return the vehicle plate number
     */
    public String getPlateNumber() {
        return plateNumber;
    }

    /**
     * Returns the vehicle brand.
     *
     * @return the vehicle brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Returns the vehicle model.
     *
     * @return the vehicle model
     */
    public String getModel() {
        return model;
    }

    /**
     * Returns the daily rental rate.
     *
     * @return the daily rental rate
     */
    public double getDailyRate() {
        return dailyRate;
    }

    /**
     * Returns the current vehicle status.
     *
     * @return the vehicle status
     */
    public VehicleStatus getStatus() {
        return status;
    }

    /**
     * Updates the vehicle status.
     *
     * @param status the new vehicle status
     */
    public void setStatus(VehicleStatus status) {
        this.status = status;
    }

    /**
     * Checks whether the vehicle is available for rental.
     *
     * @return true if the vehicle is available, false otherwise
     */
    public boolean isAvailable() {
        return status == VehicleStatus.AVAILABLE;
    }

    /**
     * Returns the vehicle type.
     *
     * @return the vehicle type
     */
    public abstract String getType();

    /**
     * Returns a text representation of the vehicle.
     *
     * @return vehicle information as text
     */
    @Override
    public String toString() {
        return id + " - " + getType() + " - " + brand + " " + model +
                " - Plate: " + plateNumber +
                " - Daily Rate: " + dailyRate;
    }
}