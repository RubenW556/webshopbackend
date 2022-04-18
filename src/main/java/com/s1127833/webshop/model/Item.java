package com.s1127833.webshop.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name= "item")
public class Item {

    @Id
    @GeneratedValue
    private long id;

    private String itemName;

    @Column(length=10485760)
    private String image;
    private float price;

    private boolean deleted = false;
}
