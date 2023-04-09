package com.online.shop.repository;

import com.online.shop.entities.ChosenProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChosenProductRepository extends JpaRepository<ChosenProduct, Integer> {

}
