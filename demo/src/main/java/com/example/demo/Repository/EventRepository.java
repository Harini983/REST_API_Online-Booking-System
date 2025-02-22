package com.example.demo.Repository;

import com.example.demo.entity.Event;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    // Find events by name
    List<Event> findByEventNameContaining(String eventName);

    // Find upcoming events
    List<Event> findByEventDateAfter(LocalDateTime now);

    // Custom JPQL query: Get events by location
    @Modifying
    @Transactional
    @Query("DELETE FROM Event e WHERE e.location = ?1")
    int deleteEventsByLocation(String location);
    // Custom JPQL query: Get recent events
    @Query("SELECT e FROM Event e ORDER BY e.eventDate DESC")
    List<Event> findRecentEvents();
}
