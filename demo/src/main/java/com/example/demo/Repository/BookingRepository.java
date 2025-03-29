package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Booking;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    // Find bookings by status
    List<Booking> findByStatus(String status);

    // Find bookings by user ID
    List<Booking> findByUserId(Long userId);

    // JPQL Query: Get bookings by bus ID
    @Query("SELECT b FROM Booking b WHERE b.busId = ?1")
    List<Booking> findBookingsByBusId(Long busId);

    // JPQL Query: Get recent bookings (sorted by booking date descending)
    @Query("SELECT b FROM Booking b ORDER BY b.bookingDate DESC")
    List<Booking> findRecentBookings();
}