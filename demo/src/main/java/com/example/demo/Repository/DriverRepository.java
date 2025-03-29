package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Driver;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    // Find drivers by name
    List<Driver> findByName(String name);
    // Find drivers with expired licenses
    List<Driver> findByLicenseExpiryDateBefore(java.time.LocalDate expiryDate);

    //Jpql
   
    @Query("SELECT d FROM Driver d WHERE d.licenseExpiryDate <= :expiryDate")
List<Driver> findDriversWithExpiringLicenses(@Param("expiryDate") LocalDate expiryDate);


   
       
    
}