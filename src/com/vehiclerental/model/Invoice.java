package com.vehiclerental.model;

import java.time.LocalDate;

public class Invoice {
    private int id;
    private Rental rental;
    private LocalDate returnDate;
    private double rentalCost;
    private double latePenalty;
    private double totalAmount;

    public Invoice(int id, Rental rental, LocalDate returnDate, double rentalCost, double latePenalty) {
        this.id = id;
        this.rental = rental;
        this.returnDate = returnDate;
        this.rentalCost = rentalCost;
        this.latePenalty = latePenalty;
        this.totalAmount = rentalCost + latePenalty;
    }

    public int getId() {
        return id;
    }

    public Rental getRental() {
        return rental;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public double getRentalCost() {
        return rentalCost;
    }

    public double getLatePenalty() {
        return latePenalty;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        return "Invoice " + id +
                "\nCustomer: " + rental.getCustomer().getName() +
                "\nVehicle: " + rental.getVehicle().getBrand() + " " + rental.getVehicle().getModel() +
                "\nRental cost: " + rentalCost +
                "\nLate penalty: " + latePenalty +
                "\nTotal amount: " + totalAmount;
    }
}