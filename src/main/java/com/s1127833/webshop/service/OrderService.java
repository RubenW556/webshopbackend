package com.s1127833.webshop.service;

import com.s1127833.webshop.enums.OrderStatus;
import com.s1127833.webshop.model.Order;
import com.s1127833.webshop.model.ShoppingCart;
import com.s1127833.webshop.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ShoppingCartService shoppingCartService;

    public OrderService(OrderRepository orderRepository, ShoppingCartService shoppingCartService){
        this.shoppingCartService = shoppingCartService;
        this.orderRepository = orderRepository;
    }

    public void putOrder(Order order){
        ShoppingCart shoppingCart = shoppingCartService.getCurrentUserShoppingCart();
        shoppingCartService.removeShoppingCart();

        order.setItems(shoppingCart.getItems());
        orderRepository.save(order);
    }

    public Order getOrder(long id){
        return orderRepository.getById(id);
    }

    public void deleteOrder(long id){
        orderRepository.deleteById(id);
    }

    public List<Order> getOrders(){
       return orderRepository.findAll();
    }

    public void updateOrder(long id, OrderStatus orderStatus){
        orderRepository.getById(id).setOrderstatus(orderStatus);
    }

}
