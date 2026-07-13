package com.vehiclerental.pricing;

import com.vehiclerental.model.Rental;

/**
 * Defines a pricing rule for calculating rental cost.
 */
public interface PricingStrategy {

    /**
     * Calculates the rental cost for a rental record.
     *
     * @param rental the rental record
     * @return the calculated rental cost
     */
    double calculateRentalCost(Rental rental);
}