package com.online.shop.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
}
