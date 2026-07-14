package com.vehiclerental.service;

import com.vehiclerental.model.Rental;
import com.vehiclerental.observer.RentalObserver;
import com.vehiclerental.repository.RentalRepository;
import com.vehiclerental.util.DateTimeProvider;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Checks rentals and notifies observers about rentals that are close to expiry.
 */
public class ReminderService {

    /**
     * Repository used to access rental records.
     */
    private RentalRepository rentalRepository;

    /**
     * Date provider used to get the current date.
     */
    private DateTimeProvider dateTimeProvider;

    /**
     * Observers that receive rental expiry notifications.
     */
    private List<RentalObserver> observers;

    /**
     * Creates a reminder service.
     *
     * @param rentalRepository the repository that stores rentals
     * @param dateTimeProvider the date provider used by the service
     */
    public ReminderService(RentalRepository rentalRepository, DateTimeProvider dateTimeProvider) {
        this.rentalRepository = rentalRepository;
        this.dateTimeProvider = dateTimeProvider;
        this.observers = new ArrayList<>();
    }

    /**
     * Adds a rental observer.
     *
     * @param observer the observer to add
     */
    public void addObserver(RentalObserver observer) {
        observers.add(observer);
    }

    /**
     * Checks active rentals that expire after a given number of days.
     *
     * @param daysBeforeEnd the number of days before the rental end date
     */
    public void checkExpiringRentals(int daysBeforeEnd) {
        LocalDate reminderDate = dateTimeProvider.today().plusDays(daysBeforeEnd);

        for (Rental rental : rentalRepository.findAll()) {
            if (rental.isActive() && rental.getEndDate().equals(reminderDate)) {
                notifyObservers(rental);
            }
        }
    }

    /**
     * Notifies all registered observers about a rental.
     *
     * @param rental the rental that is close to expiry
     */
    private void notifyObservers(Rental rental) {
        for (RentalObserver observer : observers) {
            observer.update(rental);
        }
    }
}