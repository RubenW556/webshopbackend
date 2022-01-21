package com.s1127833.webshop.service;

import com.s1127833.webshop.model.Item;
import com.s1127833.webshop.repository.ItemRepository;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Service
public class ItemService {

    private ItemRepository itemRepository;

    public ItemService (ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }
    @Transactional
    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }

    public Item getItemByID(Long id){ return itemRepository.findById(id).get(); }

    public void saveItem(Item item){
        itemRepository.save(item);
    }
}
