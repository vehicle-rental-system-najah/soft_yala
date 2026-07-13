package com.vehiclerental.model;

/**
 * Represents the current status of a rental record.
 */
public enum RentalStatus {

    /**
     * Rental is currently active.
     */
    ACTIVE,

    /**
     * Rental has been closed after vehicle return.
     */
    CLOSED,

    /**
     * Rental has been cancelled.
     */
    CANCELLED
}