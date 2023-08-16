package com.demo.controller;

import com.demo.model.User;
import com.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:63342")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }

//    @PostMapping("/register")
//    public ResponseEntity<String> registerUser(@RequestBody User user) {
//        // Check if username already exists
//        if (userRepository.findByUsername(user.getUsername()) != null) {
//            return ResponseEntity.badRequest().body("Username already exists");
//        }
//
//        // Save the user
//        userRepository.save(user);
//        return ResponseEntity.ok("User registered successfully");
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<String> loginUser(@RequestBody User user) {
//        User savedUser = (User) userRepository.findByUsername(user.getUsername());
//        if (savedUser != null && savedUser.getPassword().equals(user.getPassword())) {
//            return ResponseEntity.ok("Login successful");
//        } else {
//            return ResponseEntity.badRequest().body("Invalid username or password");
//        }
//    }

}
