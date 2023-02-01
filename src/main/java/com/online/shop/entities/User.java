package com.online.shop.entities;

import com.online.shop.enums.UserRole;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue
    private Integer id;
    private String fullName;
    private String email;
    private String password;
    private String address;
    private UserRole userRole;
}
