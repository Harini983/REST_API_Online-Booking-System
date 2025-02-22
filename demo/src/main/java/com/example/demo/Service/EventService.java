package com.example.demo.Service;

import com.example.demo.Repository.EventRepository;
import com.example.demo.entity.Event;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    // Create a new event
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    // Get all events
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // Get event by ID
    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    // Update an event
    public Event updateEvent(Long id, Event eventDetails) {
        return eventRepository.findById(id).map(event -> {
            event.setEventName(eventDetails.getEventName());
            event.setEventDate(eventDetails.getEventDate());
            event.setDescription(eventDetails.getDescription());
            event.setLocation(eventDetails.getLocation());
            return eventRepository.save(event);
        }).orElseThrow(() -> new RuntimeException("Event not found with id " + id));
    }

    // Delete an event by ID
    public boolean deleteEvent(Long id) {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Get paginated events
    public Page<Event> getPaginatedEvents(int page, int size) {
        return eventRepository.findAll(PageRequest.of(page, size));
    }

    // Get sorted events
    public List<Event> getSortedEvents(String field) {
        return eventRepository.findAll(Sort.by(Sort.Direction.DESC, field));
    }

    // Get events by name
    public List<Event> getEventsByName(String eventName) {
        return eventRepository.findByEventNameContaining(eventName);
    }

    // Get upcoming events
    public List<Event> getUpcomingEvents() {
        return eventRepository.findByEventDateAfter(LocalDateTime.now());
    }

    // Get events by location
    @Transactional
    public int deleteEventsByLocation(String location) {
        return eventRepository.deleteEventsByLocation(location);}

    // Get recent events
    public List<Event> getRecentEvents() {
        return eventRepository.findRecentEvents();
    }
}
