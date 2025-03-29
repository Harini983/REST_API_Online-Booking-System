package com.example.demo.Repository;

import com.example.demo.entity.Bus;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    public List<Bus> findByCapacity(int Capacity);
    


    public List<Bus> findByRoute(String Route);

    //jpql
    @Query("SELECT b FROM Bus b WHERE b.capacity > :capacity")
    List<Bus> findBusesByCapacityGreaterThan(@Param("capacity") int capacity);
}