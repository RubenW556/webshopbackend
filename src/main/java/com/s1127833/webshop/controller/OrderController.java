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
    public ResponseEntity<String> saveOrder(){
        orderService.putOrder();

        return new ResponseEntity<>(HttpStatus.CREATED );
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

        return new ResponseEntity<>(HttpStatus.OK );
    }

    @Secured({"ROLE_OWNER"})
    @PostMapping ("/{id}")
    public ResponseEntity<String> updateOrder(@PathVariable Integer id, @RequestParam OrderStatus orderStatus){
        long newId = id.longValue();
        orderService.updateOrder(newId, orderStatus);

        return new ResponseEntity<>(HttpStatus.OK );
    }
}
