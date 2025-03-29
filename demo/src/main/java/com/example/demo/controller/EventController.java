package com.example.demo.controller;

import com.example.demo.Service.EventService;
import com.example.demo.entity.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    // Create a new event
    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
    }

    // Get all events
    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    // Get event by ID
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Optional<Event> event = eventService.getEventById(id);
        return event.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update an event
    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event eventDetails) {
        try {
            Event updatedEvent = eventService.updateEvent(id, eventDetails);
            return ResponseEntity.ok(updatedEvent);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete an event by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        if (eventService.deleteEvent(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Get paginated events
    @GetMapping("/paginated")
    public Page<Event> getPaginatedEvents(@RequestParam int page, @RequestParam int size) {
        return eventService.getPaginatedEvents(page, size);
    }//http://localhost:8081/api/events/paginated?page=0&size=10

    // Get events by name
    @GetMapping("/name/{eventName}")
    public List<Event> getEventsByName(@PathVariable String eventName) {
        return eventService.getEventsByName(eventName);
    }//http://localhost:8081/api/events/name/AI%20Summit

    // Get upcoming events
    @GetMapping("/upcoming")
    public List<Event> getUpcomingEvents() {
        return eventService.getUpcomingEvents();
    }//http://localhost:8081/api/events/upcoming

    // Get events by location
    @DeleteMapping("/delete/{location}")
    public ResponseEntity<String> deleteEventsByLocation(@PathVariable String location) {
        int deletedCount = eventService.deleteEventsByLocation(location);
        return ResponseEntity.ok(deletedCount + " events deleted at location: " + location);}

    // Get recent events
    @GetMapping("/recent")
    public List<Event> getRecentEvents() {
        return eventService.getRecentEvents();
    }

    // Get sorted events
    @GetMapping("/sort/{field}")
    public List<Event> getSortedEvents(@PathVariable String field) {
        return eventService.getSortedEvents(field);
    }
}