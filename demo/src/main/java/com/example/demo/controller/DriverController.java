package com.example.demo.controller;

import com.example.demo.Service.DriverService;
import com.example.demo.entity.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;

    // Get all Drivers
    @GetMapping
    public List<Driver> getAllDrivers() {
        return driverService.getAllDrivers();
    }

    // Get Driver by ID
    @GetMapping("/{id}")
    public Driver getDriverById(@PathVariable Long id) {
        Optional<Driver> driver = driverService.getDriverById(id);
        return driver.orElse(null); // Return null if driver not found
    }

    // Create a new Driver
    @PostMapping
    public Driver createDriver(@RequestBody Driver driver) {
        return driverService.createDriver(driver);
    }

    // Update Driver by ID
    @PutMapping("/{id}")
    public Driver updateDriver(@PathVariable Long id, @RequestBody Driver driverDetails) {
        return driverService.updateDriver(id, driverDetails);
    }

    // Delete Driver by ID
    @DeleteMapping("/{id}")
    public void deleteDriver(@PathVariable Long id) {
        driverService.deleteDriver(id);
    }

    // Pagination for Drivers
    @GetMapping("/list")
    public Page<Driver> getPaginatedDrivers(@RequestParam int page, @RequestParam int size) {
        return driverService.getPageDrivers(page, size);
        // URL: http://localhost:8081/api/drivers/list?page=0&size=2
    }

    

    // Sort drivers by license number
    @GetMapping("/sort/{licenseNumber}")
    public List<Driver> getSorted(@PathVariable String licenseNumber) {
        return driverService.getSorted(licenseNumber);
    }
    // Example: http://localhost:8081/api/drivers/sort/licenseNumber

    // Find drivers by name
    @GetMapping("/name/{name}")
    public List<Driver> findByName(@PathVariable String name) {
        return driverService.findByName(name);
    }
    // Example: http://localhost:8081/api/drivers/name/JohnDoe

    
   
    
    // Find drivers with expired licenses
    @GetMapping("/expired-licenses")
    public List<Driver> findDriversWithExpiredLicenses() {
        return driverService.findDriversWithExpiredLicenses();
    }
    // Example: http://localhost:8081/api/drivers/expired-licenses


    //JPQL
    @GetMapping("/expiring/{expiryDate}")
    public List<Driver> getExpiringLicenses(@PathVariable String expiryDate) {
        return driverService.getExpiringLicenses(LocalDate.parse(expiryDate));
    }
}
