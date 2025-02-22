package com.example.demo.Service;

import com.example.demo.Repository.DriverRepository;
import com.example.demo.entity.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    // Create a Driver
    public Driver createDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    // Get all Drivers
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    // Get Driver by ID
    public Optional<Driver> getDriverById(Long id) {
        return driverRepository.findById(id);
    }

    // Update Driver by ID
    public Driver updateDriver(Long id, Driver driverDetails) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found"));
        driver.setName(driverDetails.getName());
        driver.setLicenseNumber(driverDetails.getLicenseNumber());
        driver.setEmail(driverDetails.getEmail());
        driver.setLicenseExpiryDate(driverDetails.getLicenseExpiryDate());
        driver.setPhone(driverDetails.getPhone());
        return driverRepository.save(driver);
    }
    // Delete Driver by ID
    public boolean deleteDriver(Long id) {
        Optional<Driver> driver = driverRepository.findById(id);
        if (driver.isPresent()) {
            driverRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Pagination for Drivers
    public Page<Driver> getPageDrivers(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return driverRepository.findAll(pageable);
    }

    // Sort drivers by license number
    public List<Driver> getSorted(String field) {
        return driverRepository.findAll(Sort.by(Sort.Direction.DESC, field));
    }

    // Find drivers by name
    public List<Driver> findByName(String name) {
        return driverRepository.findByName(name);
    }

   

    // Find drivers with expired licenses
    public List<Driver> findDriversWithExpiredLicenses() {
        return driverRepository.findByLicenseExpiryDateBefore(LocalDate.now());
    }
//JPQl
public List<Driver> getExpiringLicenses(LocalDate expiryDate) {
    return driverRepository.findDriversWithExpiringLicenses(expiryDate);
}
}

