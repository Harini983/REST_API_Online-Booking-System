package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
public class Bus {
    @Id
   
    private Long id;
    private String busNumber;
    private String route;
    private int capacity;
   

    @OneToOne(mappedBy = "assignedBus")
   @JsonIgnoreProperties("assignedBus")
    private Driver driver;

    // Constructors
    public Bus() {}

    public Bus(String busNumber, String route, int capacity, Driver driver) {
            this.busNumber = busNumber;
            this.route = route;
            this.capacity = capacity;
            this.driver = driver;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    
}