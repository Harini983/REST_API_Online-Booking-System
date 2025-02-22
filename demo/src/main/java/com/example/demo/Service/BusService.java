package com.example.demo.Service;

import com.example.demo.Repository.BusRepository;
import com.example.demo.entity.Booking;
import com.example.demo.entity.Bus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;

    // Get all buses
    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    // Get bus by ID
    public Optional<Bus> getBusById(Long id) {
        return busRepository.findById(id);
    }

    // Create a new bus
    public Bus createBus(Bus bus) {
        return busRepository.save(bus);
    }

    // Update bus by ID
    public Bus updateBus(Long id, Bus busDetails) {
        Bus bus = busRepository.findById(id).orElseThrow(() -> new RuntimeException("Bus not found"));
        bus.setBusNumber(busDetails.getBusNumber());
        bus.setRoute(busDetails.getRoute());
        bus.setCapacity(busDetails.getCapacity());
        bus.setDriverName(busDetails.getDriverName());
        return busRepository.save(bus);
    }

    // Delete bus by ID
    public void deleteBus(Long id) {
        busRepository.deleteById(id);
    }

    //sorting
     public List<Bus>getsorted(String driver_name)
    {
        return busRepository.findAll(Sort.by(Direction.ASC,driver_name) );
    }

    //JPA Custom method
    public List<Bus>findByCapacity(int Capacity)
    {
        return busRepository.findByCapacity(Capacity);
    }
    public List<Bus> findByRoute(String Route)
    {
        return busRepository.findByRoute(Route);
    }

    //jpql
    public List<Bus> findBusesByCapacityGreaterThan(int capacity) {
        return busRepository.findBusesByCapacityGreaterThan(capacity);
    }

    public static List<Booking> getBookingsByBusId(Long busId) {
        
        throw new UnsupportedOperationException("Unimplemented method 'getBookingsByBusId'");
    }
     public Page<Bus> getPageUser(int page ,int size){
        PageRequest pageable=PageRequest.of(page,size);
        return busRepository.findAll(pageable);} // pagination
}

