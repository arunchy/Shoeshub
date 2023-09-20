package com.example.database.services;


import com.example.database.pojo.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Validation {
public User validUser(List<User> users,String rawPassword){
    BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    for(User user:users){
        String encodedPassword=user.getPassword();
        if(passwordEncoder.matches(rawPassword,encodedPassword)){
            return user;
        }

    }
    return null;
}

}
