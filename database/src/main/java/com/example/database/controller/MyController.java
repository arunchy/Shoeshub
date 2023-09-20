package com.example.database.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
    @RequestMapping("/getData")
    public String getData(@RequestParam("name") String name, @RequestParam("email") String email, Model model){

      model.addAttribute("name",name);
      model.addAttribute("email",email);
      return "result";
    }
}
