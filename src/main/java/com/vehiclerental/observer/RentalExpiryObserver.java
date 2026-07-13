package com.vehiclerental.observer;

import com.vehiclerental.model.Rental;
import com.vehiclerental.notification.NotificationService;

/**
 * Observer responsible for sending rental expiry reminders.
 */
public class RentalExpiryObserver implements RentalObserver {

    /**
     * Service used to send notifications.
     */
    private NotificationService notificationService;

    /**
     * Creates a rental expiry observer.
     *
     * @param notificationService the notification service used by the observer
     */
    public RentalExpiryObserver(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    /**
     * Sends a reminder notification for a rental that is close to expiry.
     *
     * @param rental the rental that is close to expiry
     */
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