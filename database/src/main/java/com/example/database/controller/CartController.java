package com.example.database.controller;

import com.example.database.pojo.Bill;
import com.example.database.pojo.Cart;
import com.example.database.pojo.Shoes;
import com.example.database.pojo.User;
import com.example.database.repository.BillRepository;
import com.example.database.repository.CartRepository;
import com.example.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import com.example.database.repository.ShoesRepository;


@Controller
public class CartController {
    @Autowired
   private ShoesRepository shoesRepository;

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private BillRepository billRepository;


    @CrossOrigin("http://localhost:3000/")
    @GetMapping("/search/{name}")
    public ResponseEntity<List<Shoes>> searchShoes(@PathVariable("name") String name){
        System.out.println(name);
      List<Shoes> shoesData=shoesRepository.findByShoeNameStartingWith(name);
        System.out.println(shoesData.toString());
        return ResponseEntity.ok(shoesData);

    }

    @CrossOrigin("http://localhost:3000/")
    @GetMapping("getAllCart/{userId}")
    public ResponseEntity<List<Cart>> getAllCart(@PathVariable("userId") String userId){
        int intId=Integer.parseInt(userId);
        Optional<User> userOptional= userRepository.findById((long) intId);
        if(userOptional.isPresent()){
            User user=userOptional.get();
            List<Cart> cartData=cartRepository.findByuserDetails(user);
            return ResponseEntity.ok(cartData);
        }else{
            return ResponseEntity.notFound().build();
        }

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

    @CrossOrigin("http://localhost:3000/")
    @GetMapping("getShoe/{shoeId}")
    public ResponseEntity<Shoes> getShoes(@PathVariable("shoeId") String shoeId){
        int intId=Integer.parseInt(shoeId);
        Shoes data= shoesRepository.findById((long) intId).orElse(null);
        return ResponseEntity.ok(data);

    }


    @CrossOrigin("http://localhost:3000/")
    @PostMapping("/bill")
    public ResponseEntity<Object> insertBill(@RequestParam("shoeId") String shoeId,@RequestParam("cartId") String cartId,@RequestParam("size") String size,@RequestParam("quantity") String quantity,@RequestParam("userId") String userId,@RequestParam("price") String price){
        int user_id=Integer.parseInt(userId);
        int shoe_id=Integer.parseInt(shoeId);
        int cart_id=Integer.parseInt(cartId);
        int shoe_quantity=Integer.parseInt(quantity);
        int shoe_price=Integer.parseInt(price);

         User user=userRepository.findById((long) user_id ).orElse(null);
         Shoes shoe=shoesRepository.findById((long) shoe_id).orElse(null);
         Bill insertBill=new Bill();
         insertBill.setSize(size);
         insertBill.setPrize(shoe_price);
         insertBill.setUserDetails(user);
         insertBill.setQuantity(shoe_quantity);
         insertBill.setShoes_details(shoe);
         billRepository.save(insertBill);
         cartRepository.deleteBycartId((long) cart_id);
         return ResponseEntity.ok(insertBill);


    }

}
