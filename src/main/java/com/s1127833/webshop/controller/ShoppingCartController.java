package com.s1127833.webshop.controller;


import com.s1127833.webshop.model.Item;
import com.s1127833.webshop.service.ShoppingCartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ShoppingCart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService){
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping
    @Secured({"ROLE_CUSTOMER"})
    public ResponseEntity<String> addItem(@RequestBody long itemId){

        shoppingCartService.addItem(itemId);

        return new ResponseEntity<>("added item", HttpStatus.CREATED );
    }

    @DeleteMapping
    @Secured({"ROLE_CUSTOMER"})
    public ResponseEntity<String> removeItem(@RequestBody long itemId){

        shoppingCartService.removeItem(itemId);

        return new ResponseEntity<>("removed item", HttpStatus.CREATED );
    }

    @GetMapping
    @Secured({"ROLE_CUSTOMER"})
    public List<Item> getItems(){

        return Arrays.asList(shoppingCartService.getItems());
    }

}
