package com.s1127833.webshop.service;

import com.s1127833.webshop.model.ShoppingCart;
import com.s1127833.webshop.repository.ShoppingCartRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ItemService itemService;
    private final UserService userService;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, ItemService itemService, UserService userService){
        this.shoppingCartRepository = shoppingCartRepository;
        this.itemService = itemService;
        this.userService = userService;
    }

    public void addItem(Long itemId){
        ShoppingCart shoppingCart = getCurrentUserShoppingCart();
        if(shoppingCart == null){
            shoppingCart = new ShoppingCart();
            ArrayList<Long> items = new ArrayList<>();
            items.add(itemId);
            shoppingCart.setItems(items);
            shoppingCart.setUserName((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        }
        else {
            shoppingCart.addItem(itemId);
        }
        shoppingCartRepository.save(shoppingCart);
    }

    public void removeItem(Long itemId){
        ShoppingCart shoppingCart = getCurrentUserShoppingCart();
        shoppingCart.removeItem(itemService.getItemByID(itemId));
        shoppingCartRepository.save(shoppingCart);
    }

    public List<Long> getItems(){
        if(getCurrentUserShoppingCart() == null){
            return new ArrayList<Long>();
        }
        return getCurrentUserShoppingCart().getItems();
    }

    public List<Long> emptyCart(){

        return getCurrentUserShoppingCart().getItems();
    }

    public ShoppingCart getCurrentUserShoppingCart(){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return shoppingCartRepository.findByUserName(username);
    }

    public void removeShoppingCart(){
        shoppingCartRepository.delete(getCurrentUserShoppingCart());
    }

}
