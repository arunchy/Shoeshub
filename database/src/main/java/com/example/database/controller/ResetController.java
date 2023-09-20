package com.example.database.controller;


import com.example.database.pojo.User;
import com.example.database.repository.UserRepository;
import com.example.database.services.PasswordEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ResetController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoderUtil passwordEncoderUtil;



    @CrossOrigin("http://localhost:3000/")
    @PostMapping("/resetPassword")
    public ResponseEntity<User> resetPassword(@RequestParam("email") String email,@RequestParam("phoneNumber") String phoneNumber,@RequestParam("newPassword") String newPassword,@RequestParam("userId")String userId){
       int user_id=Integer.parseInt(userId);
       User user=userRepository.findById((long) user_id).orElse(null);
       if(user.getEmail().equals(email) && user.getPhoneNumber().equals(phoneNumber)){
         String encryptedPassword=passwordEncoderUtil.encryptPassword(newPassword);
         user.setPassword(encryptedPassword);
         userRepository.save(user);
         return ResponseEntity.ok(user);
       }

       return ResponseEntity.notFound().build();


    }

}
