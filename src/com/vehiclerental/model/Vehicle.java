package com.vehiclerental.model;

public abstract class Vehicle {
    private int id;
    private String plateNumber;
    private String brand;
    private String model;
    private double dailyRate;
    private VehicleStatus status;

    public Vehicle(int id, String plateNumber, String brand, String model, double dailyRate) {
        this.id = id;
        this.plateNumber = plateNumber;
        this.brand = brand;
        this.model = model;
        this.dailyRate = dailyRate;
        this.status = VehicleStatus.AVAILABLE;
    }

    public int getId() {
        return id;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public VehicleStatus getStatus() {
        return status;
    }

    public void setStatus(VehicleStatus status) {
        this.status = status;
    }

    public boolean isAvailable() {
        return status == VehicleStatus.AVAILABLE;
    }

    public abstract String getType();

    @Override
    public String toString() {
        return id + " - " + getType() + " - " + brand + " " + model +
                " - Plate: " + plateNumber +
                " - Daily Rate: " + dailyRate;
    }
}