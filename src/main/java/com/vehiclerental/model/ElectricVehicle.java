package com.vehiclerental.model;

/**
 * Represents an electric vehicle type.
 */
public class ElectricVehicle extends Vehicle {

    /**
     * The battery level percentage.
     */
    private int batteryLevel;

    /**
     * Shows whether the battery was checked before rental.
     */
    private boolean batteryChecked;

    /**
     * Creates an electric vehicle.
     *
     * @param id the electric vehicle id
     * @param plateNumber the electric vehicle plate number
     * @param brand the electric vehicle brand
     * @param model the electric vehicle model
     * @param dailyRate the daily rental rate
     * @param batteryLevel the battery level percentage
     * @param batteryChecked true if the battery was checked, false otherwise
     */
    public ElectricVehicle(int id, String plateNumber, String brand, String model,
                           double dailyRate, int batteryLevel, boolean batteryChecked) {
        super(id, plateNumber, brand, model, dailyRate);
        this.batteryLevel = batteryLevel;
        this.batteryChecked = batteryChecked;
    }

    /**
     * Returns the battery level.
     *
     * @return the battery level percentage
     */
    public int getBatteryLevel() {
        return batteryLevel;
    }

    /**
     * Checks whether the battery was checked.
     *
     * @return true if the battery was checked, false otherwise
     */
    public boolean isBatteryChecked() {
        return batteryChecked;
    }

    /**
     * Updates the battery level.
     *
     * @param batteryLevel the new battery level
     */
    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    /**
     * Updates the battery check status.
     *
     * @param batteryChecked the new battery check status
     */
    public void setBatteryChecked(boolean batteryChecked) {
        this.batteryChecked = batteryChecked;
    }

    /**
     * Returns the vehicle type.
     *
     * @return the vehicle type
     */
    @Override
    public String getType() {
        return "Electric Vehicle";
    }
}