package com.vehiclerental.service;

import com.vehiclerental.model.Car;
import com.vehiclerental.model.Customer;
import com.vehiclerental.model.Rental;
import com.vehiclerental.notification.NotificationService;
import com.vehiclerental.observer.RentalExpiryObserver;
import com.vehiclerental.repository.InMemoryRentalRepository;
import com.vehiclerental.repository.RentalRepository;
import com.vehiclerental.util.DateTimeProvider;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.mockito.Mockito.*;

class ReminderServiceTest {

    @Test
    void checkExpiringRentalsShouldSendReminder() {
        RentalRepository rentalRepository = new InMemoryRentalRepository();

        Customer customer = new Customer(
                1,
                "Lina Ahmad",
                "0599000000",
                "lina@example.com",
                25
        );

        Car car = new Car(1, "PAL-100", "Toyota", "Corolla", 35.0);

        Rental rental = new Rental(
                1,
                customer,
                car,
                LocalDate.of(2026, Month.JULY, 13),
                LocalDate.of(2026, Month.JULY, 14)
        );

        rentalRepository.save(rental);

        DateTimeProvider dateTimeProvider = mock(DateTimeProvider.class);
        NotificationService notificationService = mock(NotificationService.class);

        when(dateTimeProvider.today()).thenReturn(LocalDate.of(2026, Month.JULY, 13));

        ReminderService reminderService = new ReminderService(rentalRepository, dateTimeProvider);
        reminderService.addObserver(new RentalExpiryObserver(notificationService));

        reminderService.checkExpiringRentals(1);

        verify(notificationService, times(1)).sendNotification(
                eq("lina@example.com"),
                eq("Rental Expiry Reminder"),
                contains("Toyota Corolla")
        );
    }

    @Test
    void checkExpiringRentalsShouldNotSendReminderForDifferentDate() {
        RentalRepository rentalRepository = new InMemoryRentalRepository();

        Customer customer = new Customer(
                1,
                "Lina Ahmad",
                "0599000000",
                "lina@example.com",
                25
        );

        Car car = new Car(1, "PAL-100", "Toyota", "Corolla", 35.0);

        Rental rental = new Rental(
                1,
                customer,
                car,
                LocalDate.of(2026, Month.JULY, 13),
                LocalDate.of(2026, Month.JULY, 20)
        );

        rentalRepository.save(rental);

        DateTimeProvider dateTimeProvider = mock(DateTimeProvider.class);
        NotificationService notificationService = mock(NotificationService.class);

        when(dateTimeProvider.today()).thenReturn(LocalDate.of(2026, Month.JULY, 13));

        ReminderService reminderService = new ReminderService(rentalRepository, dateTimeProvider);
        reminderService.addObserver(new RentalExpiryObserver(notificationService));

        reminderService.checkExpiringRentals(1);

        verify(notificationService, never()).sendNotification(
                anyString(),
                anyString(),
                anyString()
        );
    }
}