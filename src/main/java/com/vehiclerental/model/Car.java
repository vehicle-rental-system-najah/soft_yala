package com.vehiclerental.model;

public class Car extends Vehicle {

    public Car(int id, String plateNumber, String brand, String model, double dailyRate) {
        super(id, plateNumber, brand, model, dailyRate);
    }

    @Override
    public String getType() {
        return "Car";
    }
}