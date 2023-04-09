package com.online.shop.repository;

import com.online.shop.entities.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Integer> {


}
