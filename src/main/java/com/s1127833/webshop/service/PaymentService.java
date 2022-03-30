package com.s1127833.webshop.service;

import com.s1127833.webshop.model.Payment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    ShoppingCartService shoppingCartService;

    PaymentService (ShoppingCartService shoppingCartService, ItemService itemService){
        this.shoppingCartService = shoppingCartService;
    }

    public Payment createAndGetPayment(){
        Payment payment = new Payment();

        List<Long> shoppingCart = shoppingCartService.getCurrentUserShoppingCart().getItems();

        int price = 0;
        for(int i =0; i<shoppingCart.size(); i++){
            price += shoppingCart.get(i);
        }
        payment.setPrice(price);

        return payment;
    }
}
