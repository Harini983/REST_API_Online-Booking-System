package com.example.demo.controller;

import com.example.demo.entity.Admin;
import com.example.demo.Service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Get all Admins
    @GetMapping("/admins")
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    // Get Admin by ID
    @GetMapping("admins/{id}")
    public Admin getAdminById(@PathVariable Long id) {
        return adminService.getAdminById(id).orElse(null); // return null if admin not found
    }

    // Create a new Admin
    @PostMapping("/admins")
    public Admin createAdmin(@RequestBody Admin admin) {
        return adminService.createAdmin(admin);
    }

    // Update Admin by ID
    @PutMapping("admins/{id}")
    public Admin updateAdmin(@PathVariable Long id, @RequestBody Admin adminDetails) {
        return adminService.updateAdmin(id, adminDetails);
    }

    // Delete Admin by ID
    @DeleteMapping("admins/{id}")
    public void deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
    }

    @GetMapping("sorting/{name}")
   
    
    public List<Admin> getSorted(@PathVariable String name)
    {
        return adminService.getSorted(name);
    }

     @GetMapping("/customname/{name}")
    public List<Admin>findByname(@PathVariable String name)
    {
        return adminService.findByName(name);
    }
   
    @GetMapping("/customPassword/{Password}")
    public List<Admin>findByPassword(@PathVariable String Password)
    {
        return adminService.findByPassword(Password);
    }
    //http://localhost:8081/api/customPassword/harini
    
    //jpql
    @GetMapping("/getByEmail/{email}")
    public Admin getAdminByEmail(@PathVariable String email) {
        return adminService.getAdminByEmail(email);
    }
    //http://localhost:8081/api/getByEmail/admin2@example.com
}