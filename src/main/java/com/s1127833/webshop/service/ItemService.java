package com.s1127833.webshop.service;

import com.s1127833.webshop.model.Item;
import com.s1127833.webshop.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private ItemRepository itemRepository;

    public ItemService (ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }
    public Item getItemByID(int id){
        return itemRepository.getById(id);
    }

    public void saveItem(Item item){
        itemRepository.save(item);
    }
}
