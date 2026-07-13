package com.vehiclerental.observer;

import com.vehiclerental.model.Rental;
import com.vehiclerental.notification.NotificationService;

public class RentalExpiryObserver implements RentalObserver {
    private NotificationService notificationService;

    public RentalExpiryObserver(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    public void update(Rental rental) {
        String receiver = rental.getCustomer().getEmail();

        if (receiver == null || receiver.isEmpty()) {
            receiver = rental.getCustomer().getPhone();
        }

        String subject = "Rental Expiry Reminder";
        String message = "Dear " + rental.getCustomer().getName()
                + ", your rental for "
                + rental.getVehicle().getBrand() + " "
                + rental.getVehicle().getModel()
                + " will end on " + rental.getEndDate() + ".";

        notificationService.sendNotification(receiver, subject, message);
    }
}