package com.example.BookShop.Service;

import com.example.BookShop.Entities.User;
import com.example.BookShop.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(User user) throws ExecutionException, IllegalArgumentException, InterruptedException {
        if(!userRepository.existsById(user.getUserId())){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }else{
            throw new IllegalArgumentException("Something went wrong!! try again");
        }
    }
}
