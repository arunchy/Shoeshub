package com.example.database.controller;


import com.example.database.pojo.Bill;
import com.example.database.pojo.User;
import com.example.database.repository.BillRepository;
import com.example.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProfileController {
   @Autowired
    private UserRepository userRepository;
   @Autowired
   private BillRepository billRepository;


    @CrossOrigin("http://localhost:3000/")
    @GetMapping("/gallery/{userId}")
    public ResponseEntity<List<Bill>> getUserGallery(@PathVariable("userId") String userId){
        int user_id=Integer.parseInt(userId);
        User user= userRepository.findById((long) user_id).orElse(null);
        List<Bill> data=billRepository.findByuserDetails(user);
        return ResponseEntity.ok(data);


    }


}
