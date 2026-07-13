package com.vehiclerental.notification;

/**
 * Sends notifications using email.
 */
public class EmailNotificationService implements NotificationService {

    /**
     * Sends an email notification.
     *
     * @param receiver the receiver email address
     * @param subject the email subject
     * @param message the email message
     */
    @Override
    public void sendNotification(String receiver, String subject, String message) {
        System.out.println("Email sent to: " + receiver);
        System.out.println("Subject: " + subject);
        System.out.println("Message: " + message);
    }
}