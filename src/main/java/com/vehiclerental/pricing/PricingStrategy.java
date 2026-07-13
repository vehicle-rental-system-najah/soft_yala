package com.vehiclerental.pricing;

import com.vehiclerental.model.Rental;

public interface PricingStrategy {
    double calculateRentalCost(Rental rental);
}