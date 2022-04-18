package com.s1127833.webshop.service;

import com.s1127833.webshop.model.Item;
import com.s1127833.webshop.repository.ItemRepository;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final SanitizationService sanitizationService;

    public ItemService (ItemRepository itemRepository, SanitizationService sanitizationService){
        this.itemRepository = itemRepository;
        this.sanitizationService = sanitizationService;
    }

    @Transactional
    public List<Item> getAllItems(){
        return itemRepository.findByDeletedFalse();
    }

    public Item getItemByID(Long id){ return itemRepository.findById(id).get();}

    public void saveItem(Item item){
        item.setItemName(sanitizationService.Sanitize(item.getItemName()));

        itemRepository.save(item);
    }

    public void deleteItem(long itemId){
        Item item  = itemRepository.getById(itemId);
        item.setDeleted(true);
        itemRepository.save(item);
    }

    public void updateItem(Item item, long itemID){
        Item temp = itemRepository.getById(itemID);
        temp.setImage(item.getImage());
        temp.setItemName(item.getItemName());
        temp.setPrice(item.getPrice());
        saveItem(temp);
    }
}
