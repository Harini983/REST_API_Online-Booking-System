package com.example.demo.Service;

import com.example.demo.entity.Booking;
import com.example.demo.Repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    // Create a new booking
    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    // Get all bookings
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Get booking by ID
    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    // Update an existing booking
    public Booking updateBooking(Long id, Booking bookingDetails) {
        return bookingRepository.findById(id).map(booking -> {
            booking.setUserId(bookingDetails.getUserId());
            booking.setBusId(bookingDetails.getBusId());
            booking.setSeatNumber(bookingDetails.getSeatNumber());
            booking.setBookingDate(bookingDetails.getBookingDate());
            booking.setStatus(bookingDetails.getStatus());
            return bookingRepository.save(booking);
        }).orElseThrow(() -> new RuntimeException("Booking not found with id " + id));
    }

    // Delete a booking by ID
    public boolean deleteBooking(Long id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Get paginated bookings
    public Page<Booking> getPaginatedBookings(int page, int size) {
        return bookingRepository.findAll(PageRequest.of(page, size));
    }

    // Get sorted bookings
    public List<Booking> getSortedBookings(String field) {
        return bookingRepository.findAll(Sort.by(Sort.Direction.DESC, field));
    }

    // Get bookings by status
    public List<Booking> getBookingsByStatus(String status) {
        return bookingRepository.findByStatus(status);
    }

    // Get bookings by user ID
    public List<Booking> getBookingsByUserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    // Get bookings by bus ID (JPQL)
    public List<Booking> getBookingsByBusId(Long busId) {
        return bookingRepository.findBookingsByBusId(busId);
    }

    // Get recent bookings (JPQL)
    public List<Booking> getRecentBookings() {
        return bookingRepository.findRecentBookings();
    }
     //sorting 
    public List<Booking>getsorted(String name)
    {
        return bookingRepository.findAll(Sort.by(Direction.ASC,name) );
    }
}
