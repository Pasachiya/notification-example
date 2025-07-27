package com.example.notificationapp.listener;

import com.example.notificationapp.model.Notification;
import com.example.notificationapp.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {

    @Autowired
    private NotificationService notificationService;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = headerAccessor.getSessionId();

        System.out.println("New WebSocket connection established: " + sessionId);

        // Send welcome notification
        Notification welcomeNotification = new Notification(
                "Welcome!",
                "You are now connected to real-time notifications. Session: " + sessionId,
                "CONNECTION",
                "SYSTEM"
        );

        notificationService.saveNotification(welcomeNotification);
        notificationService.broadcastNotification(welcomeNotification);
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = headerAccessor.getSessionId();

        System.out.println("WebSocket connection closed: " + sessionId);

        // Optionally send disconnect notification
        Notification disconnectNotification = new Notification(
                "User Disconnected",
                "A user has disconnected from the notification system",
                "DISCONNECTION",
                "SYSTEM"
        );

        notificationService.saveNotification(disconnectNotification);
        notificationService.broadcastNotification(disconnectNotification);
    }
}