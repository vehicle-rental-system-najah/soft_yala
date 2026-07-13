package com.vehiclerental.observer;

import com.vehiclerental.model.Rental;

public interface RentalObserver {
    void update(Rental rental);
}