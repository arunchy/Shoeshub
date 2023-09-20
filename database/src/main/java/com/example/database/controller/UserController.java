package com.example.database.controller;

import com.example.database.pojo.User;
import com.example.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    @GetMapping("/user")
    public String insertUser(){
        User data=new User();
        data.setFirstName("Arun");
        data.setLastName("Chuadhary");
        data.setEmail("arunchy600@gmail.com");
        data.setPassword("123456");
        data.setPhoneNumber("9821638230");
        data.setCity("Kathmandu");
        data.setState("Bagmati");
        data.setStreetAddress("Maitidevi");
        data.setProfilePicture("/");
        Date dob= Date.valueOf("2003-05-03");
        data.setDateOfBirth(dob);
        Date today=Date.valueOf("2023-07-13");
        data.setRegistrationDate(today);
        Date login=Date.valueOf("2023-07-13");
        data.setLastLogin(login);
        data.setUserType("Admin");
        User userData=userRepository.save(data);
        return userData.getUserId().toString();



    }

}
