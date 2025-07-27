package com.example.notificationapp.controller;

import com.example.notificationapp.model.Notification;
import com.example.notificationapp.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // Handle incoming messages from clients
    @MessageMapping("/send")
    @SendTo("/topic/notifications")
    public Notification sendNotification(Notification notification) {
        System.out.println("Received notification: " + notification);

        // Process the notification
        notification.setSender("Client");
        notification.setType("USER_MESSAGE");

        // Save notification (in memory for this demo)
        notificationService.saveNotification(notification);

        return notification;
    }

    // REST endpoint to send notifications
    @PostMapping("/api/notifications/send")
    @ResponseBody
    public Notification sendNotificationRest(@RequestBody Notification notification) {
        notification.setSender("REST_API");
        notification.setType("API_MESSAGE");

        // Save notification
        notificationService.saveNotification(notification);

        // Broadcast to all connected clients
        messagingTemplate.convertAndSend("/topic/notifications", notification);

        return notification;
    }

    // Get all notifications
    @GetMapping("/api/notifications")
    @ResponseBody
    public List<Notification> getAllNotifications() {
        return notificationService.getAllNotifications();
    }

    // Send system notification
    @PostMapping("/api/notifications/system")
    @ResponseBody
    public Notification sendSystemNotification(@RequestParam String message) {
        Notification notification = new Notification(
                "System Notification",
                message,
                "SYSTEM_MESSAGE",
                "SYSTEM"
        );

        notificationService.saveNotification(notification);
        messagingTemplate.convertAndSend("/topic/notifications", notification);

        return notification;
    }
}
