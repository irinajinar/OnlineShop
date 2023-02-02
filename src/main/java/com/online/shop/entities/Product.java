package com.online.shop.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;
    private String category;
    private Double price;
    private String description;

    @Lob
    @Column(columnDefinition="BLOB")
    private byte[] image;

    private Integer quantity;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<ChosenProduct> chosenProducts;



}
