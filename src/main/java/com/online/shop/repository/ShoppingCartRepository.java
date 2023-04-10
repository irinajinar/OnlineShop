package com.online.shop.repository;

import com.online.shop.entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends
        JpaRepository<ShoppingCart, Integer> {

    ShoppingCart findByUserEmail(String email);

}
