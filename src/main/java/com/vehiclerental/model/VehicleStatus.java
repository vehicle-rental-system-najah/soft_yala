package com.vehiclerental.model;

/**
 * Represents the current status of a vehicle.
 */
public enum VehicleStatus {

    /**
     * Vehicle is available for rent.
     */
    AVAILABLE,

    /**
     * Vehicle is currently rented.
     */
    RENTED,

    /**
     * Vehicle is under maintenance.
     */
    MAINTENANCE
}