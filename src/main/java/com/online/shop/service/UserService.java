package com.online.shop.service;


import com.online.shop.dto.ProductDto;
import com.online.shop.dto.UserDetailsDto;
import com.online.shop.dto.UserDto;
import com.online.shop.entities.User;
import com.online.shop.mapper.UserMapper;
import com.online.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRepository userRepository;

    public void registerUser(UserDto userDto) {
        User user = userMapper.map(userDto);
        userRepository.save(user);
    }


    public UserDetailsDto getUserDetailsDtoByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        User user = optionalUser.get();
        UserDetailsDto userDetailsDto = new UserDetailsDto();
        userDetailsDto.setAddress(user.getAddress());
        userDetailsDto.setFullName(user.getFullName());
        return userDetailsDto;
    }


}
