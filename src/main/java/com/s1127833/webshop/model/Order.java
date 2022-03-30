package com.s1127833.webshop.model;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.s1127833.webshop.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name= "itemOrder")
public class Order {

    @Id
    @GeneratedValue
    private long id;

    @ElementCollection
    private List<Long> items;

    @OneToOne(cascade=CascadeType.ALL)
    @JsonManagedReference
    private Payment payment;

    private String username;
    private int price;
    private OrderStatus orderstatus = OrderStatus.PROCESSED;
}
