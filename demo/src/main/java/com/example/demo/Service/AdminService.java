package com.example.demo.Service;

import com.example.demo.Repository.AdminRepository;
import com.example.demo.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    // Create a new Admin
    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    // Get all Admins
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    // Pagination for Admins
    public Page<Admin> getAdminsPaginated(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return adminRepository.findAll(pageable); // pagination logic
    }

    // Get Admin by ID
    public Optional<Admin> getAdminById(Long id) {
        return adminRepository.findById(id);
    }

    // Delete Admin by ID
    public boolean deleteAdmin(Long id) {
        if (adminRepository.existsById(id)) {
            adminRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Update Admin Details
    public Admin updateAdmin(Long id, Admin adminDetails) {
        return adminRepository.findById(id).map(admin -> {
            admin.setName(adminDetails.getName());
            admin.setEmail(adminDetails.getEmail());
            admin.setPassword(adminDetails.getPassword()); // Consider hashing password
            return adminRepository.save(admin);
        }).orElseThrow(() -> new RuntimeException("Admin not found with ID: " + id));
    }

    // Get sorted Admins by name
    public List<Admin> getSorted(String name) {
        return adminRepository.findAll(Sort.by(Direction.DESC, name));
    }

    // JPA Custom method
    public List<Admin> findByName(String name) {
        return adminRepository.findByname(name);
    }

    public List<Admin> findByPassword(String password) {
        return adminRepository.findByPassword(password);
    }

    //jpql
    public Admin getAdminByEmail(String email) {
        return adminRepository.findByEmail(email);}
}
