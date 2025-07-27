package com.example.notificationapp.service;

import com.example.notificationapp.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class NotificationService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // In-memory storage for demo purposes
    private final List<Notification> notifications = new CopyOnWriteArrayList<>();

    public void saveNotification(Notification notification) {
        notifications.add(notification);

        // Keep only last 100 notifications
        if (notifications.size() > 100) {
            notifications.remove(0);
        }

        System.out.println("Saved notification: " + notification);
    }

    public List<Notification> getAllNotifications() {
        return new ArrayList<>(notifications);
    }

    public void broadcastNotification(Notification notification) {
        messagingTemplate.convertAndSend("/topic/notifications", notification);
    }

    // Send periodic system notifications
    @Scheduled(fixedRate = 30000) // Every 30 seconds
    public void sendPeriodicNotification() {
        String[] messages = {
                "System is running smoothly",
                "Database backup completed successfully",
                "New updates are available",
                "Server performance is optimal",
                "Security scan completed - all clear"
        };

        String randomMessage = messages[(int) (Math.random() * messages.length)];

        Notification notification = new Notification(
                "Scheduled Update",
                randomMessage,
                "SYSTEM_SCHEDULED",
                "SCHEDULER"
        );

        saveNotification(notification);
        broadcastNotification(notification);
    }
}