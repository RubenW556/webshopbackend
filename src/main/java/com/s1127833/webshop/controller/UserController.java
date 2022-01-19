package com.s1127833.webshop.controller;

import com.s1127833.webshop.model.UserAccount;
import com.s1127833.webshop.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.net.http.HttpResponse;

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
    public ResponseEntity<String> createUser(@RequestBody UserAccount userDetails){
        userDetails.setPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
        if(userDetails.getAuthorities().get(0).getAuthority().equals( "OWNER")){
            UserAccount userAccount = (UserAccount) SecurityContextHolder
                    .getContext().getAuthentication().getPrincipal();
            if(userAccount.getRole().equals( "OWNER")){
                return new ResponseEntity<>("can't create owner without being an owner", HttpStatus.FORBIDDEN );
            }
        }
        userService.createUser(userDetails);
        return new ResponseEntity<>("created user account", HttpStatus.CREATED );
    }
}
