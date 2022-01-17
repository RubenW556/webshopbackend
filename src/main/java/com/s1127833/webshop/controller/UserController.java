package com.s1127833.webshop.controller;

import com.s1127833.webshop.model.UserAccount;
import com.s1127833.webshop.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
@RequestMapping("/user")
public class UserController {
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserService userService;

    public UserController(BCryptPasswordEncoder encoder, UserService userService){
        bCryptPasswordEncoder = encoder;
        this.userService = userService;
    }
    @PostMapping
    public UserAccount createUser(@RequestBody UserAccount UserAccount){
        UserAccount.setPassword(bCryptPasswordEncoder.encode(UserAccount.getPassword()));
        return userService.createUser(UserAccount);
    }

    @GetMapping
    public String createUser(){
        return "hah";
    }
}
