package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity

public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long busId;
    private int seatNumber;
    private LocalDate bookingDate;
    private String status;

    // Proper One-to-Many Relationship
    @ManyToOne(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")  // Match with User ID
    @JsonIgnoreProperties("bookings")
    private User user;

    public Booking() {}

    public Booking(User user, Long busId, int seatNumber, LocalDate bookingDate, String status) {
        this.user = user;
        this.busId = busId;
        this.seatNumber = seatNumber;
        this.bookingDate = bookingDate;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getBusId() { return busId; }
    public void setBusId(Long busId) { this.busId = busId; }

    public int getSeatNumber() { return seatNumber; }
    public void setSeatNumber(int seatNumber) { this.seatNumber = seatNumber; }

    public LocalDate getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDate bookingDate) { this.bookingDate = bookingDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}