package com.s1127833.webshop.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Payment {

    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    @JsonBackReference
    private Order order;

    private int price;
}
