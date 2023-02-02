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
    private Integer chosenProduct;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private ShoppingCart shoppingCart;

    private Integer chosenQuantity;

    @ManyToOne
    @JoinColumn
    private Product product;
}
