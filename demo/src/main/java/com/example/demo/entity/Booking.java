package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long busId;
    private int seatNumber;
    private LocalDate bookingDate;
    private String status;

    public Booking() {}

    public Booking(Long userId, Long busId, int seatNumber, LocalDate bookingDate, String status) {
        this.userId = userId;
        this.busId = busId;
        this.seatNumber = seatNumber;
        this.bookingDate = bookingDate;
        this.status = status;
    }

    public Long getId(){
        return id; 
    }
    public void setId(Long id) {
         this.id = id; 
        }
    public Long getUserId() { 
        return userId; 
    }
    public void setUserId(Long userId) { 
        this.userId = userId; 
    }
    public Long getBusId() { return busId; }
    public void setBusId(Long busId) { this.busId = busId; }
    public int getSeatNumber() { return seatNumber; }
    public void setSeatNumber(int seatNumber) { this.seatNumber = seatNumber; }
    public LocalDate getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDate bookingDate) { this.bookingDate = bookingDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
