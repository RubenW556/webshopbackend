package com.s1127833.webshop.service;

import com.s1127833.webshop.model.Item;
import com.s1127833.webshop.model.ShoppingCart;
import com.s1127833.webshop.model.UserAccount;
import com.s1127833.webshop.repository.ShoppingCartRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ItemService itemService;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, ItemService itemService){
        this.shoppingCartRepository = shoppingCartRepository;
        this.itemService = itemService;
    }

    public void addItem(Long itemId){
        ShoppingCart shoppingCart = getCurrentUserShoppingCart();
        Item item = itemService.getItemByID(itemId);
        if(shoppingCart == null){
            shoppingCart = new ShoppingCart();
            Item[] items = new Item[1];
            items[0] = item;
            shoppingCart.setItems(items);
        }
        shoppingCart.addItem(item);
        shoppingCartRepository.save(shoppingCart);
    }

    public void removeItem(Long itemId){
        ShoppingCart shoppingCart = getCurrentUserShoppingCart();
        shoppingCart.removeItem(itemService.getItemByID(itemId));
        shoppingCartRepository.save(shoppingCart);
    }

    public Item[] getItems(){

        return getCurrentUserShoppingCart().getItems();
    }

    public ShoppingCart getCurrentUserShoppingCart(){
        UserAccount userAccount = (UserAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return shoppingCartRepository.findByUserId(userAccount.getUsername());
    }

    public void removeShoppingCart(){
        shoppingCartRepository.delete(getCurrentUserShoppingCart());
    }

}
