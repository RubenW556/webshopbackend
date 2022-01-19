package com.s1127833.webshop.controller;

import com.s1127833.webshop.model.Item;
import com.s1127833.webshop.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    private ItemService itemService;

    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> getAllItems(){
        return itemService.getAllItems();
    }

    @GetMapping("/{id}")
    public Item getItem(@PathVariable("id") int itemId){
        return itemService.getItemByID(itemId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> getAllItems(@PathVariable("id") int itemId,@RequestBody Item item){
        Item temp = itemService.getItemByID(itemId);
        temp.setImage(item.getImage());
        temp.setItemName(item.getItemName());
        temp.setPrice(item.getPrice());
        itemService.saveItem(temp);

        return new ResponseEntity<>("created user account", HttpStatus.CREATED );
    }
    @PostMapping
    public ResponseEntity<String> getAllItems(@RequestBody Item item){
        itemService.saveItem(item);

        return new ResponseEntity<>("created user account", HttpStatus.CREATED );
    }

}
