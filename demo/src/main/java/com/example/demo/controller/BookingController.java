package com.example.demo.controller;

import com.example.demo.entity.Booking;
import com.example.demo.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // Create a new booking
    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        return bookingService.createBooking(booking);
    }

    // Get all bookings
    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    // Get booking by ID
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        Optional<Booking> booking = bookingService.getBookingById(id);
        return booking.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update an existing booking
    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable Long id, @RequestBody Booking bookingDetails) {
        try {
            Booking updatedBooking = bookingService.updateBooking(id, bookingDetails);
            return ResponseEntity.ok(updatedBooking);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a booking by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        if (bookingService.deleteBooking(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Get paginated bookings
    @GetMapping("/paginated")
    public Page<Booking> getPaginatedBookings(@RequestParam int page, @RequestParam int size) {
        return bookingService.getPaginatedBookings(page, size);
    }//http://localhost:8081/api/bookings/paginated?page=0&size=02

    // Get bookings by status
    @GetMapping("/status/{status}")
    public List<Booking> getBookingsByStatus(@PathVariable String status) {
        return bookingService.getBookingsByStatus(status);
    }//http://localhost:8081/api/bookings/status/CONFIRMED

    // Get bookings by user ID
    @GetMapping("/user/{userId}")
    public List<Booking> getBookingsByUserId(@PathVariable Long userId) {
        return bookingService.getBookingsByUserId(userId);
    }

    // Get bookings by bus ID (JPQL)
    @GetMapping("/bus/{busId}")
    public List<Booking> getBookingsByBusId(@PathVariable Long busId) {
        return bookingService.getBookingsByBusId(busId);
    }

    // Get recent bookings (JPQL)
    @GetMapping("/recent")
    public List<Booking> getRecentBookings() {
        return bookingService.getRecentBookings();
    }//http://localhost:8081/api/bookings/recent

    //Sorting
    @GetMapping("/sort/{name}")
   
    
    public List<Booking>getSorted(@PathVariable String name)
    {
        return bookingService.getsorted(name);
    }//http://localhost:8081/api/bookings/sort/seatNumber
}
