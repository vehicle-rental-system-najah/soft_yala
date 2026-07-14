package com.vehiclerental.observer;

import com.vehiclerental.model.Rental;

/**
 * Defines an observer that reacts to rental updates.
 */
public interface RentalObserver {

    /**
     * Updates the observer with rental information.
     *
     * @param rental the rental that triggered the update
     */
    void update(Rental rental);
}