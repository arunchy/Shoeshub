package com.example.database.controller;

import com.example.database.pojo.User;
import com.example.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class UpdateController {
    @Autowired
    private UserRepository userRepository;



    @CrossOrigin("http://localhost:3000/")
    @RequestMapping("/updateUser")
    public ResponseEntity<User> updateUser(@RequestParam("userId") String userId, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("email") String email, @RequestParam("phoneNumber") String phoneNumber, @RequestParam("city") String city, @RequestParam("state") String state, @RequestParam("profilePicture") String profilePicture){
        int user_id=Integer.parseInt(userId);
        User user=userRepository.findById((long) user_id).orElse(null);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setCity(city);
        user.setState(state);
        user.setProfilePicture(profilePicture);
        userRepository.save(user);
        return ResponseEntity.ok(user);

    }

}
