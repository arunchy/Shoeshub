package com.example.database.controller;

import com.example.database.pojo.Cart;
import com.example.database.pojo.Shoes;
import com.example.database.pojo.User;
import com.example.database.repository.*;
import com.example.database.services.FileServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class ViewController {

    @Autowired
    private FileServices fileServices;
    @Value("${project.image}")
    private String path;


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TempFileRepository tempFileRepository;

    @Autowired
    private ShoesRepository shoesRepository;

    @Autowired
    private BrandsRepository brandsRepository;

    @Autowired
    private CartRepository cartRepository;


    @CrossOrigin("http://localhost:3000/")
    @GetMapping("getShoe/{shoeId}")
    public ResponseEntity<Shoes> getShoes(@PathVariable("shoeId") String shoeId){
        int intId=Integer.parseInt(shoeId);
        Shoes data= shoesRepository.findById((long) intId).orElse(null);
        return ResponseEntity.ok(data);

    }

    @CrossOrigin("http://localhost:3000/")
    @PostMapping("/addToCart")
    public ResponseEntity<Cart> cart(@RequestParam("userId") String userId, @RequestParam("shoeDetails") String  shoeDetails){
        System.out.println("User ID: "+userId);
        int intUserId=Integer.parseInt(userId);
        long longUserId=(long) intUserId;
        User user=userRepository.findById(longUserId).orElse(null);
        System.out.println(user);


        int shoeId=Integer.parseInt(shoeDetails);
        Shoes shoe=shoesRepository.findById((long) shoeId).orElse(null);
        System.out.println(shoe);


        Cart cart=new Cart();
        cart.setUserDetails(user);
        cart.setShoes_details(shoe);
        Cart data= cartRepository.save(cart);
        return ResponseEntity.ok(data);

    }

}
