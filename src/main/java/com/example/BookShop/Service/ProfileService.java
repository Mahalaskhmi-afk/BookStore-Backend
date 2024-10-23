package com.example.BookShop.Service;

import com.example.BookShop.Entities.User;
import com.example.BookShop.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ProfileService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User updateProfile(int bookId, UserDto userDto) throws IOException {
        if (userRepository.existsById(bookId)) {
            User user = new User();
            user.setUserId(userDto.getUserId());
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setEmail(userDto.getEmail());
            user.setGender(userDto.getGender());
            user.setAddress(userDto.getAddress());
            user.setImage(userDto.getImage().getBytes());
            user.setCity(userDto.getCity());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setState(userDto.getState());
            user.setCountry(userDto.getCountry());
            user.setPinCode(userDto.getPinCode());
            user.setRole(userDto.getRole());
            return userRepository.save(user);
        } else {
            throw new IllegalArgumentException("Error loading page");
        }
    }

}
