package com.example.demo.Repository;

import com.example.demo.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    // Find notifications by user ID
    List<Notification> findByUserId(Long userId);

    // Find unread notifications for a user
    List<Notification> findByUserIdAndIsReadFalse(Long userId);

    // JPQL Query: Get recent notifications for a user
    @Query("SELECT n FROM Notification n WHERE n.userId = ?1 ORDER BY n.timestamp DESC")
    List<Notification> findRecentNotificationsByUserId(Long userId);
}
