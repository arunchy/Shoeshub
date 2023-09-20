package com.example.database.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Signup {
    @PostMapping("/userSignup")
    public String signup(@RequestParam("firstname") String firstName,@RequestParam("lastname")String lastName){
        System.out.println(firstName+" "+lastName);
        return "i got the values";

    }

}
