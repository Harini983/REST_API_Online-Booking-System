package com.example.demo.controller;

import com.example.demo.Service.BusService;
import com.example.demo.entity.Bus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class BusController {

    @Autowired
    private BusService busService;

    // Get all buses
    @GetMapping("/buses")
    public List<Bus> getAllBuses() {
        return busService.getAllBuses();
    }

    // Get bus by ID
    @GetMapping("/buses/{id}")
    public Bus getBusById(@PathVariable Long id) {
        return busService.getBusById(id).orElse(null); // return null if bus not found
    }

    // Create a new bus
    @PostMapping("/buses")
    public Bus createBus(@RequestBody Bus bus) {
        return busService.createBus(bus);
    }

    // Update bus by ID
    @PutMapping("/buses/{id}")
    public Bus updateBus(@PathVariable Long id, @RequestBody Bus busDetails) {
        return busService.updateBus(id, busDetails);
    }

    // Delete bus by ID
    @DeleteMapping("/buses/{id}")
    public void deleteBus(@PathVariable Long id) {
        busService.deleteBus(id);
    }

    //sorting
    @GetMapping("/sortBus/{driver_name}")
    public List<Bus>getSorted(@PathVariable String driver_name)
    {
        return busService.getsorted(driver_name);//sorting
    }
    //http://localhost:8081/api/sortBus/driverName

    @GetMapping("/customCapacity/{Capacity}")
    public List<Bus>findByCapacity(@PathVariable int Capacity)
    {
        return busService.findByCapacity(Capacity);
    }
    //http://localhost:8081/api/customCapacity/50
    @GetMapping("/customRoute/{Route}")
    public List<Bus>findByRoute(@PathVariable String Route)
    {
        return busService.findByRoute(Route);
    }
    //http://localhost:8081/api/customRoute/Route 1

    //jpql
    @GetMapping("/buses/capacityGreaterThan/{capacity}")
    public List<Bus> getBusesByCapacityGreaterThan(@PathVariable int capacity) {
        return busService.findBusesByCapacityGreaterThan(capacity);
    }
    // Pagination 
    @GetMapping("/users/pagei") 
    public Page<Bus> getUsers(@RequestParam int page, @RequestParam int size) {
        return busService.getPageUser(page, size);
        
    }

}
