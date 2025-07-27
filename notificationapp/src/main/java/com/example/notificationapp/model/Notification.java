package com.example.notificationapp.model;

import java.time.LocalDateTime;

public class Notification {
    private String id;
    private String title;
    private String message;
    private String type;
    private LocalDateTime timestamp;
    private String sender;

    // Default constructor
    public Notification() {
        this.timestamp = LocalDateTime.now();
    }

    // Constructor with parameters
    public Notification(String title, String message, String type, String sender) {
        this.title = title;
        this.message = message;
        this.type = type;
        this.sender = sender;
        this.timestamp = LocalDateTime.now();
        this.id = generateId();
    }

    private String generateId() {
        return "notif_" + System.currentTimeMillis() + "_" + (int)(Math.random() * 1000);
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public String getSender() { return sender; }
    public void setSender(String sender) { this.sender = sender; }

    @Override
    public String toString() {
        return "Notification{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", type='" + type + '\'' +
                ", timestamp=" + timestamp +
                ", sender='" + sender + '\'' +
                '}';
    }
}
