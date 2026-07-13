package com.vehiclerental.model;

import java.time.LocalDate;

/**
 * Represents a rental record between a customer and a vehicle.
 */
public class Rental {

    /**
     * The rental id.
     */
    private int id;

    /**
     * The customer who rents the vehicle.
     */
    private Customer customer;

    /**
     * The rented vehicle.
     */
    private Vehicle vehicle;

    /**
     * The rental start date.
     */
    private LocalDate startDate;

    /**
     * The rental end date.
     */
    private LocalDate endDate;

    /**
     * The current rental status.
     */
    private RentalStatus status;

    /**
     * Creates a rental record.
     *
     * @param id the rental id
     * @param customer the customer who rents the vehicle
     * @param vehicle the rented vehicle
     * @param startDate the rental start date
     * @param endDate the rental end date
     */
    public Rental(int id, Customer customer, Vehicle vehicle, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.customer = customer;
        this.vehicle = vehicle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = RentalStatus.ACTIVE;
    }

    /**
     * Returns the rental id.
     *
     * @return the rental id
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the rental customer.
     *
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Returns the rented vehicle.
     *
     * @return the vehicle
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * Returns the rental start date.
     *
     * @return the start date
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Returns the rental end date.
     *
     * @return the end date
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Returns the rental status.
     *
     * @return the rental status
     */
    public RentalStatus getStatus() {
        return status;
    }

    /**
     * Checks whether the rental is active.
     *
     * @return true if the rental is active, false otherwise
     */
    public boolean isActive() {
        return status == RentalStatus.ACTIVE;
    }

    /**
     * Closes the rental record.
     */
    public void close() {
        status = RentalStatus.CLOSED;
    }

    /**
     * Returns a text representation of the rental.
     *
     * @return rental information as text
     */
    @Override
    public String toString() {
        return "Rental " + id + " - " + customer.getName() + " rented " +
                vehicle.getBrand() + " " + vehicle.getModel() +
                " from " + startDate + " to " + endDate;
    }
}