package com.vehiclerental.model;

public class Truck extends Vehicle {
    private double maxLoadKg;

    public Truck(int id, String plateNumber, String brand, String model,
                 double dailyRate, double maxLoadKg) {
        super(id, plateNumber, brand, model, dailyRate);
        this.maxLoadKg = maxLoadKg;
    }

    public double getMaxLoadKg() {
        return maxLoadKg;
    }

    @Override
    public String getType() {
        return "Truck";
    }
}