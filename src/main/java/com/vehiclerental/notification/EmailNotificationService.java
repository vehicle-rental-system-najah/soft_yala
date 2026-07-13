package com.vehiclerental.notification;

public class EmailNotificationService implements NotificationService {

    @Override
    public void sendNotification(String receiver, String subject, String message) {
        System.out.println("Email sent to: " + receiver);
        System.out.println("Subject: " + subject);
        System.out.println("Message: " + message);
    }
}