package com.online.shop.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class ChosenProduct {
    @Id
    @GeneratedValue
    private Integer id;

    private Integer chosenQuantity;

    @ManyToOne
    @JoinColumn
    private Product product;

    @ManyToOne
    @JoinColumn
    private ShoppingCart shoppingCart;

    @ManyToOne
    @JoinColumn
    private CustomerOrder customerOrder;
}
