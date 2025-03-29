package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Driver {

    @Id
    private Long id;

    private String name;
    private String licenseNumber;
    private String phone;
    private String email;
    private LocalDate licenseExpiryDate;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bus_id", nullable = false, unique = true)
   @JsonIgnoreProperties("driver")
    private Bus assignedBus; 
    
    public Driver() {}

    public Driver(String name, String licenseNumber, String phone, String email, LocalDate licenseExpiryDate, Bus assignedBus) {
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

    public Bus getAssignedBus() { return assignedBus; }
    public void setAssignedBus(Bus assignedBus) { this.assignedBus = assignedBus; }
}