package com.s1127833.webshop.service;

import com.s1127833.webshop.model.Order;
import com.s1127833.webshop.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;

    }

    public void putOrder(Order Order){
        orderRepository.save(Order);
    }

    public Order getOrder(int id){
        return orderRepository.getById(id);
    }

    public void deleteOrder(int id){
        orderRepository.deleteById(id);
    }

}
