package com.s1127833.webshop.controller;

import com.s1127833.webshop.model.Item;
import com.s1127833.webshop.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> getAllItems(){
        return itemService.getAllItems();
    }

    @GetMapping("/{id}")
    public Item getItem(@PathVariable("id") Long itemId){ return itemService.getItemByID(itemId);}

    @Secured("ROLE_OWNER")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateItem(@PathVariable("id") Long itemId,@RequestBody Item item){
        itemService.updateItem(item, itemId);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Secured("ROLE_OWNER")
    @PostMapping
    public ResponseEntity<String> saveItem(@RequestBody Item item){
        itemService.saveItem(item);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
