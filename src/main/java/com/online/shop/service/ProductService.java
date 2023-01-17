package com.online.shop.service;


import com.online.shop.dto.ProductDto;
import com.online.shop.entities.Product;
import com.online.shop.mapper.ProductMapper;
import com.online.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    public void addProduct(ProductDto productDto){
       Product product= productMapper.map(productDto);
       productRepository.save(product);
    }
}
