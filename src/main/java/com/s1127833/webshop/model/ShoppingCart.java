package com.s1127833.webshop.model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private Item[] items;

    private long userId;

    public void addItem(Item newItem){
        int n = items.length+1;

        Item[] newItems = new Item[n];
        for (int i = 0; i < n; i++)
            newItems[i] = items[i];

        newItems[n] = newItem;

        items = newItems;
    }

    public void removeItem(Item removeItem){
        int n = items.length-1;

        Item[] newItems = new Item[n];
        for (int i = 0; i < n; i++) {
            int j = 0;
            if (items[i].equals(removeItem)) {
                j = 1;
            }
            newItems[i] = items[i + j];
        }

        items = newItems;
    }
}
