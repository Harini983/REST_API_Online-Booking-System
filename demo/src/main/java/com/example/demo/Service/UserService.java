package com.example.demo.Service;

import com.example.demo.Repository.UserRepository;
import com.example.demo.entity.User;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    
        public List<User> getAllUsers() {
            return userRepository.findAll();
        }
    
        public  Optional<User> getUserById(Long id) {
            return userRepository.findById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    public Page<User>getPageUser(int page ,int size){
        PageRequest pageable=PageRequest.of(page,size);
        return userRepository.findAll(pageable); // pagination
    }

    //sorting 
    public List<User>getsorted(String name)
    {
        return userRepository.findAll(Sort.by(Direction.ASC,name) );
    }

    //JPA Custom method
    public List<User>findByUsername(String Username)
    {
        return userRepository.findByusername(Username);
    }
    public List<User>findByEmail(String email)
    {
        return userRepository.findByEmail(email);
    }

    public List<User>findUserByUsername(String username)
    {
        return userRepository.findUserByUsername(username);
    }
}
