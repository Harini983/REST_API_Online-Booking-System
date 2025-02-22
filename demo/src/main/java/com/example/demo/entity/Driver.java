package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Driver {

    @Id
    private Long id;

    private String name;
    private String licenseNumber;
    private String phone;
    private String email;
    private LocalDate licenseExpiryDate;
    private String assignedBus; 
    
    public Driver() {}

    public Driver(String name, String licenseNumber, String phone, String email, LocalDate licenseExpiryDate, String assignedBus) {
        this.name = name;
        this.licenseNumber = licenseNumber;
        this.phone = phone;
        this.email = email;
        this.licenseExpiryDate = licenseExpiryDate;
        this.assignedBus = assignedBus;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLicenseNumber() { return licenseNumber; }
    public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDate getLicenseExpiryDate() { return licenseExpiryDate; }
    public void setLicenseExpiryDate(LocalDate licenseExpiryDate) { this.licenseExpiryDate = licenseExpiryDate; }

    public String getAssignedBus() { return assignedBus; }
    public void setAssignedBus(String assignedBus) { this.assignedBus = assignedBus; }
}
