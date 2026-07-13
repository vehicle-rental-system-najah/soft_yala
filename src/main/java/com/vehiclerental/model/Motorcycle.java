package com.vehiclerental.model;

public class Motorcycle extends Vehicle {
    private int engineCapacity;

    public Motorcycle(int id, String plateNumber, String brand, String model,
                      double dailyRate, int engineCapacity) {
        super(id, plateNumber, brand, model, dailyRate);
        this.engineCapacity = engineCapacity;
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    @Override
    public String getType() {
        return "Motorcycle";
    }
}