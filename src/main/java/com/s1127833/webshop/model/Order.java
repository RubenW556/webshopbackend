package com.s1127833.webshop.model;


import javax.persistence.*;
import java.util.Map;

@Entity(name= "itemorder")
public class Order {

    @Id
    @GeneratedValue
    private int id;

    @ElementCollection
    private Map<Integer, Integer> items;

    private long userId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<Integer, Integer> getItems() {
        return items;
    }

    public void setItems(Map<Integer, Integer> items) {
        this.items = items;
    }


}
