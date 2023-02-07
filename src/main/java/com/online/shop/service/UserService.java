package com.online.shop.service;


import com.online.shop.dto.ProductDto;
import com.online.shop.dto.UserDto;
import com.online.shop.entities.User;
import com.online.shop.mapper.UserMapper;
import com.online.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRepository userRepository;

    public void register(UserDto userDto){
        User user = userMapper.map(userDto);
        userRepository.save(user);

    }
}
