package com.vehiclerental.service;

import com.vehiclerental.model.Rental;
import com.vehiclerental.repository.RentalRepository;
import com.vehiclerental.util.DateTimeProvider;
import com.vehiclerental.observer.RentalObserver;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReminderService {
    private RentalRepository rentalRepository;
    private DateTimeProvider dateTimeProvider;
    private List<RentalObserver> observers;

    public ReminderService(RentalRepository rentalRepository, DateTimeProvider dateTimeProvider) {
        this.rentalRepository = rentalRepository;
        this.dateTimeProvider = dateTimeProvider;
        this.observers = new ArrayList<>();
    }

    public void addObserver(RentalObserver observer) {
        observers.add(observer);
    }

    public void checkExpiringRentals(int daysBeforeEnd) {
        LocalDate reminderDate = dateTimeProvider.today().plusDays(daysBeforeEnd);

        for (Rental rental : rentalRepository.findAll()) {
            if (rental.isActive() && rental.getEndDate().equals(reminderDate)) {
                notifyObservers(rental);
            }
        }
    }

    private void notifyObservers(Rental rental) {
        for (RentalObserver observer : observers) {
            observer.update(rental);
        }
    }
}