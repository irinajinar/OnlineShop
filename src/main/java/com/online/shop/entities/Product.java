package com.online.shop.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
    private Integer quantity;
    @Lob
    @Column(columnDefinition="BLOB")
    private byte[] image;
}
