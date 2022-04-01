package com.s1127833.webshop.service;

import com.s1127833.webshop.enums.OrderStatus;
import com.s1127833.webshop.model.Order;
import com.s1127833.webshop.model.Payment;
import com.s1127833.webshop.model.ShoppingCart;
import com.s1127833.webshop.repository.OrderRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ShoppingCartService shoppingCartService;
    private final PaymentService paymentService;

    public OrderService(OrderRepository orderRepository, ShoppingCartService shoppingCartService, PaymentService paymentService){
        this.shoppingCartService = shoppingCartService;
        this.orderRepository = orderRepository;
        this.paymentService = paymentService;
    }

    public void putOrder( ){
        ShoppingCart shoppingCart = shoppingCartService.getCurrentUserShoppingCart();

        Order order = new Order();
        List<Long> item = new ArrayList<>();
        item.addAll(shoppingCart.getItems());
        order.setItems(item);
        Payment payment = paymentService.createAndGetPayment();
        order.setPrice(payment.getPrice());
        payment.setOrder(order);
        order.setPayment(payment);
        order.setUsername((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        orderRepository.save(order);
        shoppingCartService.emptyCart();
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
        Order order = orderRepository.getById(id);
        order.setOrderstatus(orderStatus);
        orderRepository.save(order);
    }

}
