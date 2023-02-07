package com.online.shop.mapper;

import com.online.shop.dto.UserDto;
import com.online.shop.entities.ShoppingCart;
import com.online.shop.entities.User;
import com.online.shop.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public User map(UserDto userDto){
        User user = new User();
        user.setFullName(userDto.getFullName());
        user.setEmail(userDto.getEmail());
        String passwordEncoded = bCryptPasswordEncoder.encode(userDto.getPassword());
        user.setPassword(passwordEncoded);
        user.setAddress(userDto.getAddress());
        user.setUserRole(UserRole.valueOf(userDto.getUserRole()));
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        user.setShoppingCart(shoppingCart);
        return user;

    }
}
