package com.online.shop.validator;

import com.online.shop.dto.UserDto;
import com.online.shop.entities.User;
import com.online.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Optional;

@Service
public class UserValidator {
    @Autowired
    private UserRepository userRepository;

    public void validate(UserDto userDto, BindingResult bindingResult) {
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if (optionalUser.isPresent()) {
            FieldError fieldError = new FieldError("userDto", "email",
                    "Email is already in use!");
            bindingResult.addError(fieldError);
        }
    }
}
