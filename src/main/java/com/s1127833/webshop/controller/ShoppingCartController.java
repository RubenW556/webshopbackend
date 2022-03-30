package com.s1127833.webshop.controller;

import com.s1127833.webshop.service.ShoppingCartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> addItem(@RequestBody Long itemId){
        shoppingCartService.addItem(itemId);

        return new ResponseEntity<>(HttpStatus.CREATED );
    }

    @DeleteMapping("/{id}")
    @Secured({"ROLE_CUSTOMER"})
    public ResponseEntity<String> removeItem(@RequestBody long itemId){
        shoppingCartService.removeItem(itemId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    @Secured({"ROLE_CUSTOMER"})
    public ResponseEntity<String> emptyCart(){
        shoppingCartService.emptyCart();

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    @Secured({"ROLE_CUSTOMER"})
    public List<Long> getItems(){
        return shoppingCartService.getItems();
    }

}
