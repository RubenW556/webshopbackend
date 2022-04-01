package com.s1127833.webshop.model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue
    private long id;

    @ElementCollection
    private List<Long> items;

    private String userName;

    public void addItem(Long newItem){
        this.items.add(newItem);
    }

    public void removeItem(Item removeItem){
        int n = items.size();
        for (int i = 0; i < n; i++) {
            if (items.get(i).equals(removeItem)) {
                items.remove(i-1);
            }
        }
    }
}
