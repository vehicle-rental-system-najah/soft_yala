package com.vehiclerental.model;

import java.time.LocalDate;

/**
 * Represents an invoice generated after returning a vehicle.
 */
public class Invoice {

    /**
     * The invoice id.
     */
    private int id;

    /**
     * The rental related to this invoice.
     */
    private Rental rental;

    /**
     * The actual vehicle return date.
     */
    private LocalDate returnDate;

    /**
     * The rental cost before penalties.
     */
    private double rentalCost;

    /**
     * The late return penalty.
     */
    private double latePenalty;

    /**
     * The total invoice amount.
     */
    private double totalAmount;

    /**
     * Creates an invoice.
     *
     * @param id the invoice id
     * @param rental the rental related to the invoice
     * @param returnDate the actual return date
     * @param rentalCost the rental cost
     * @param latePenalty the late return penalty
     */
    public Invoice(int id, Rental rental, LocalDate returnDate, double rentalCost, double latePenalty) {
        this.id = id;
        this.rental = rental;
        this.returnDate = returnDate;
        this.rentalCost = rentalCost;
        this.latePenalty = latePenalty;
        this.totalAmount = rentalCost + latePenalty;
    }

    /**
     * Returns the invoice id.
     *
     * @return the invoice id
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the rental related to this invoice.
     *
     * @return the rental
     */
    public Rental getRental() {
        return rental;
    }

    /**
     * Returns the actual return date.
     *
     * @return the return date
     */
    public LocalDate getReturnDate() {
        return returnDate;
    }

    /**
     * Returns the rental cost.
     *
     * @return the rental cost
     */
    public double getRentalCost() {
        return rentalCost;
    }

    /**
     * Returns the late return penalty.
     *
     * @return the late penalty
     */
    public double getLatePenalty() {
        return latePenalty;
    }

    /**
     * Returns the total invoice amount.
     *
     * @return the total amount
     */
    public double getTotalAmount() {
        return totalAmount;
    }

    /**
     * Returns a text representation of the invoice.
     *
     * @return invoice information as text
     */
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