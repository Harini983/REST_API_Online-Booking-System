package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    public List<Admin> findByname(String name);
    public List<Admin> findByPassword(String password);

    //jpql
    @Query("SELECT a FROM Admin a WHERE a.email = :email")
Admin findByEmail(@Param("email") String email);

}