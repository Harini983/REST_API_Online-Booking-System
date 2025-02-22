package com.example.demo.controller;

import com.example.demo.Service.NotificationService;
import com.example.demo.entity.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    // Create a new notification
    @PostMapping
    public Notification createNotification(@RequestBody Notification notification) {
        return notificationService.createNotification(notification);
    }

    // Get all notifications
    @GetMapping
    public List<Notification> getAllNotifications() {
        return notificationService.getAllNotifications();
    }

    // Get notification by ID
    @GetMapping("/{id}")
    public ResponseEntity<Notification> getNotificationById(@PathVariable Long id) {
        Optional<Notification> notification = notificationService.getNotificationById(id);
        return notification.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a notification (mark as read)
    @PutMapping("/{id}")
    public ResponseEntity<Notification> updateNotification(@PathVariable Long id, @RequestBody Notification notificationDetails) {
        try {
            Notification updatedNotification = notificationService.updateNotification(id, notificationDetails);
            return ResponseEntity.ok(updatedNotification);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a notification by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
        if (notificationService.deleteNotification(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Get paginated notifications
    @GetMapping("/paginated")
    public Page<Notification> getPaginatedNotifications(@RequestParam int page, @RequestParam int size) {
        return notificationService.getPaginatedNotifications(page, size);
    }//http://localhost:8081/api/notifications/paginated?page=0&size=5

    // Get sorted notifications
    @GetMapping("/sort/{field}")
    public List<Notification> getSortedNotifications(@PathVariable String field) {
        return notificationService.getSortedNotifications(field);
    }//http://localhost:8081/api/notifications/sort/timestamp

    // Get notifications by user ID
    @GetMapping("/user/{userId}")
    public List<Notification> getNotificationsByUserId(@PathVariable Long userId) {
        return notificationService.getNotificationsByUserId(userId);
    }//http://localhost:8081/api/notifications/user/102

    // Get unread notifications for a user
    @GetMapping("/user/{userId}/unread")
    public List<Notification> getUnreadNotifications(@PathVariable Long userId) {
        return notificationService.getUnreadNotifications(userId);
    }//http://localhost:8081/api/notifications/user/101/unread

    // Get recent notifications for a user
    @GetMapping("/user/{userId}/recent")
    public List<Notification> getRecentNotificationsByUserId(@PathVariable Long userId) {
        return notificationService.getRecentNotificationsByUserId(userId);
    }//http://localhost:8081/api/notifications/user/101/recent
}
