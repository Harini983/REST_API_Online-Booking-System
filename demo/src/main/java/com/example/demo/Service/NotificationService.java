package com.example.demo.Service;

import com.example.demo.Repository.NotificationRepository;
import com.example.demo.entity.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    // Create a new notification
    public Notification createNotification(Notification notification) {
        notification.setTimestamp(LocalDateTime.now());
        return notificationRepository.save(notification);
    }

    // Get all notifications
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    // Get notification by ID
    public Optional<Notification> getNotificationById(Long id) {
        return notificationRepository.findById(id);
    }

    // Update a notification (mark as read)
    public Notification updateNotification(Long id, Notification notificationDetails) {
        return notificationRepository.findById(id).map(notification -> {
            notification.setMessage(notificationDetails.getMessage());
            notification.setRead(notificationDetails.isRead());
            return notificationRepository.save(notification);
        }).orElseThrow(() -> new RuntimeException("Notification not found with id " + id));
    }

    // Delete a notification by ID
    public boolean deleteNotification(Long id) {
        if (notificationRepository.existsById(id)) {
            notificationRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Get paginated notifications
    public Page<Notification> getPaginatedNotifications(int page, int size) {
        return notificationRepository.findAll(PageRequest.of(page, size));
    }

    // Get sorted notifications
    public List<Notification> getSortedNotifications(String field) {
        return notificationRepository.findAll(Sort.by(Sort.Direction.DESC, field));
    }

    // Get notifications by user ID
    public List<Notification> getNotificationsByUserId(Long userId) {
        return notificationRepository.findByUserId(userId);
    }

    // Get unread notifications for a user
    public List<Notification> getUnreadNotifications(Long userId) {
        return notificationRepository.findByUserIdAndIsReadFalse(userId);
    }

    // Get recent notifications for a user
    public List<Notification> getRecentNotificationsByUserId(Long userId) {
        return notificationRepository.findRecentNotificationsByUserId(userId);
    }


}