package com.s1127833.webshop.controller;

import com.s1127833.webshop.enums.OrderStatus;
import com.s1127833.webshop.model.Order;
import com.s1127833.webshop.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService ){
        this.orderService = orderService;
    }

    @PostMapping
    @Secured({"ROLE_CUSTOMER"})
    public ResponseEntity<String> saveOrder(@RequestBody Order order){

        orderService.putOrder(order);

        return new ResponseEntity<>("created order", HttpStatus.CREATED );
    }

    @Secured({"ROLE_OWNER"})
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable long id){

        return new ResponseEntity<>(orderService.getOrder(id), HttpStatus.OK );
    }

    @Secured({"ROLE_OWNER"})
    @GetMapping()
    public ResponseEntity<List<Order>> getOrders(){

        return new ResponseEntity<>(orderService.getOrders(), HttpStatus.OK );
    }

    @Secured({"ROLE_OWNER"})
    @DeleteMapping ("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable long id){
        orderService.deleteOrder(id);

        return new ResponseEntity<>("deleted order", HttpStatus.OK );
    }

    @Secured({"ROLE_OWNER"})
    @PutMapping ("/{id}")
    public ResponseEntity<String> updateOrder(@PathVariable long id, @RequestBody OrderStatus orderStatus){
        orderService.updateOrder(id, orderStatus);

        return new ResponseEntity<>("deleted order", HttpStatus.OK );
    }
}
