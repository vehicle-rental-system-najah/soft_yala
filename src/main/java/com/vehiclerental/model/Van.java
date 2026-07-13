package com.vehiclerental.model;

public class Van extends Vehicle {
    private int seats;

    public Van(int id, String plateNumber, String brand, String model,
               double dailyRate, int seats) {
        super(id, plateNumber, brand, model, dailyRate);
        this.seats = seats;
    }

    public int getSeats() {
        return seats;
    }

    @Override
    public String getType() {
        return "Van";
    }
}