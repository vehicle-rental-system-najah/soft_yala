package com.vehiclerental.model;

import java.time.LocalDate;

public class Rental {
    private int id;
    private Customer customer;
    private Vehicle vehicle;
    private LocalDate startDate;
    private LocalDate endDate;
    private RentalStatus status;

    public Rental(int id, Customer customer, Vehicle vehicle, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.customer = customer;
        this.vehicle = vehicle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = RentalStatus.ACTIVE;
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public RentalStatus getStatus() {
        return status;
    }

    public boolean isActive() {
        return status == RentalStatus.ACTIVE;
    }

    public void close() {
        status = RentalStatus.CLOSED;
    }

    @Override
    public String toString() {
        return "Rental " + id + " - " + customer.getName() + " rented " +
                vehicle.getBrand() + " " + vehicle.getModel() +
                " from " + startDate + " to " + endDate;
    }
}