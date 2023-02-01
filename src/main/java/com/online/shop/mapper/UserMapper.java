package com.online.shop.mapper;

import com.online.shop.dto.UserDto;
import com.online.shop.entities.User;
import com.online.shop.enums.UserRole;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public User map(UserDto userDto){
        User user = new User();
        user.setFullName(userDto.getFullName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAddress(userDto.getAddress());
        user.setUserRole(UserRole.valueOf(userDto.getUserRole()));
        return user;


    }
}
