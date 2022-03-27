package com.s1127833.webshop.model;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.s1127833.webshop.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name= "itemOrder")
public class Order {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private Item[] items;

    @OneToOne
    @JsonManagedReference
    private Payment payment;

    private long userId;
    private int price;
    private OrderStatus orderstatus = OrderStatus.PROCESSED;
}
