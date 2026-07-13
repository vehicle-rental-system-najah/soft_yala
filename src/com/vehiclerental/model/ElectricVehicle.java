package com.vehiclerental.model;

public class ElectricVehicle extends Vehicle {
    private int batteryLevel;
    private boolean batteryChecked;

    public ElectricVehicle(int id, String plateNumber, String brand, String model,
                           double dailyRate, int batteryLevel, boolean batteryChecked) {
        super(id, plateNumber, brand, model, dailyRate);
        this.batteryLevel = batteryLevel;
        this.batteryChecked = batteryChecked;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public boolean isBatteryChecked() {
        return batteryChecked;
    }

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public void setBatteryChecked(boolean batteryChecked) {
        this.batteryChecked = batteryChecked;
    }

    @Override
    public String getType() {
        return "Electric Vehicle";
    }
}