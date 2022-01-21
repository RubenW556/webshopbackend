package com.s1127833.webshop.controller;

import com.s1127833.webshop.model.Item;
import com.s1127833.webshop.model.Order;
import com.s1127833.webshop.model.UserAccount;
import com.s1127833.webshop.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService ){
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<String> saveOrder(@RequestBody Integer[] order){
        Order temp = new Order();
//        order.setUserId(((UserAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
        HashMap<Integer, Integer> temp2 = new HashMap<>();
        for(int i=0; i<order.length; i++){
            temp2.put(order[i], 1);
        }
        temp.setItems(temp2);
        orderService.putOrder(temp);

        return new ResponseEntity<>("created order", HttpStatus.CREATED );
    }
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable int id){

        return new ResponseEntity<>(orderService.getOrder(id), HttpStatus.OK );
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable int id){
        orderService.deleteOrder(id);


        return new ResponseEntity<>("deleted order", HttpStatus.OK );
    }




}
