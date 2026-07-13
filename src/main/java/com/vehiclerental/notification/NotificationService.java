package com.vehiclerental.notification;

/**
 * Defines notification operations used by the system.
 */
public interface NotificationService {

    /**
     * Sends a notification message to a receiver.
     *
     * @param receiver the receiver email or phone number
     * @param subject the notification subject
     * @param message the notification message
     */
    void sendNotification(String receiver, String subject, String message);
}