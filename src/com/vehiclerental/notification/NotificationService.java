package com.vehiclerental.notification;

public interface NotificationService {
    void sendNotification(String receiver, String subject, String message);
}