package com.example.notificationapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NotificationappApplication {
	public static void main(String[] args) {
		SpringApplication.run(NotificationappApplication.class, args);
	}
}