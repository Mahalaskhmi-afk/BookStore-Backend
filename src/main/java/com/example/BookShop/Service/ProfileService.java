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

    public User updateProfile(User user) throws IOException {

        User existingUser = userRepository.findById(user.getUserId()).orElse(null);
        if(existingUser != null){
            return userRepository.save(user);
        }else{
            throw new IllegalArgumentException("Something went wrong!! try again");
        }
    }

}
