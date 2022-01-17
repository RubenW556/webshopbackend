package com.s1127833.webshop.model;

import javax.persistence.*;

@Entity(name= "item")
public class Item {

    @Id
    @GeneratedValue
    private long id;

    private String itemName;
    private Byte[] image;
    private float price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}