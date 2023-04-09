package com.online.shop.service;

import com.online.shop.dto.ChosenProductDto;
import com.online.shop.dto.ShoppingCartDto;
import com.online.shop.dto.ShoppingCartItemDto;
import com.online.shop.entities.ChosenProduct;
import com.online.shop.entities.Product;
import com.online.shop.entities.ShoppingCart;
import com.online.shop.entities.User;
import com.online.shop.repository.ChosenProductRepository;
import com.online.shop.repository.ProductRepository;
import com.online.shop.repository.ShoppingCartRepository;
import com.online.shop.repository.UserRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppingCartService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChosenProductRepository chosenProductRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    public void addToCart(ChosenProductDto chosenProductDto, String productId, String loggedInUserEmail) {
        ChosenProduct chosenProduct = buildChosenProduct(chosenProductDto, productId, loggedInUserEmail);
        chosenProductRepository.save(chosenProduct);
    }

    private ChosenProduct buildChosenProduct(ChosenProductDto chosenProductDto, String productId, String loggedInUserEmail) {
        ChosenProduct chosenProduct = new ChosenProduct();
        chosenProduct.setChosenQuantity(Integer.valueOf(chosenProductDto.getQuantity()));
        Optional<Product> optionalProduct = productRepository.findById(Integer.valueOf(productId));
        chosenProduct.setProduct(optionalProduct.get());
        Optional<User> optionalUser = userRepository.findByEmail(loggedInUserEmail);
        chosenProduct.setShoppingCart(optionalUser.get().getShoppingCart());
        return chosenProduct;
    }

    public ShoppingCartDto getShoppingCartDtoByUserEmail(String loggedInUserEmail) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserEmail(loggedInUserEmail);
        ShoppingCartDto shoppingCartDto = new ShoppingCartDto();
        double subTotal = 0;
        for (ChosenProduct chosenProduct : shoppingCart.getChosenProducts()) {
            ShoppingCartItemDto shoppingCartItemDto = new ShoppingCartItemDto();
            shoppingCartItemDto.setName(chosenProduct.getProduct().getName());
            shoppingCartItemDto.setQuantity(String.valueOf(chosenProduct.getChosenQuantity()));
            shoppingCartItemDto.setPrice(String.valueOf(chosenProduct.getProduct().getPrice()));
            double auxiliaryPrice = chosenProduct.getChosenQuantity() * chosenProduct.getProduct().getPrice();
            shoppingCartItemDto.setTotal(String.valueOf(auxiliaryPrice));
            subTotal = subTotal + auxiliaryPrice;
            shoppingCartItemDto.setImage(Base64.encodeBase64String(chosenProduct.getProduct().getImage()));
            shoppingCartDto.add(shoppingCartItemDto);
        }
        shoppingCartDto.setSubTotal(String.valueOf(subTotal));
        shoppingCartDto.setTotal(String.valueOf(subTotal + 50));
        return shoppingCartDto;

    }

}
