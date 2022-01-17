package com.s1127833.webshop.controller;

import com.s1127833.webshop.model.User;
import com.s1127833.webshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
@RequestMapping("/userController")
public class UserController {
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserService userService;

    public UserController(BCryptPasswordEncoder encoder, UserService userService){
        bCryptPasswordEncoder = encoder;
        this.userService = userService;
    }

    public User createUser(@RequestBody User User){
        User.setPassword(bCryptPasswordEncoder.encode(User.getPassword()));
        return userService.createUser(User);
    }
}
