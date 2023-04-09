package com.online.shop.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class CustomerOrder {

    @Id
    @GeneratedValue
    private Integer id;

    private String shippingAddress;

    @ManyToOne
    @JoinColumn
    private User user;

    @OneToMany(mappedBy = "customerOrder")
    private List<ChosenProduct> chosenProducts;

}