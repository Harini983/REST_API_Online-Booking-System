// UserController.java (Controller Layer)
package com.example.demo.controller;

import com.example.demo.entity.User;

import jakarta.validation.ConstraintViolationException;

import com.example.demo.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;




@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("users/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id).orElse(null); // return null if user not found
    }
 
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("users/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        return userService.updateUser(id, userDetails);
    }

    @DeleteMapping("users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    // Pagination 
    @GetMapping("/users/page") 
    public Page<User> getUsers(@RequestParam int page, @RequestParam int size) {
        return userService.getPageUser(page, size);
        // URL: http://localhost:8081/{api}/users/page?page=0&size=2
    }

    //Sorting
    @GetMapping("/sort/{name}")
   
    
    public List<User>getSorted(@PathVariable String name)
    {
        return userService.getsorted(name);
    }
    //http://localhost:8081/api/sort/username

    @GetMapping("/customName/{userName}")
    public List<User>findByUsername(@PathVariable String userName)
    {
        return userService.findByUsername(userName);
    }
    //http://localhost:8081/api/custom/john_doe
    @GetMapping("/customEmail/{email}")
    public List<User>findByEmail(@PathVariable String email)
    {
        return userService.findByEmail(email);
    }
    //http://localhost:8081/api/customEmail/david@example.com
    
    @GetMapping("/get/{Username}")
    public List<User>findUserByUsername(@PathVariable String Username)
    {
        return userService.findUserByUsername(Username);
    }

  //VALIDATION
  @ExceptionHandler(ConstraintViolationException.class)
  public String handleValidationException(ConstraintViolationException ex) {
      return "Validation Error: " + ex.getMessage();
  }
}