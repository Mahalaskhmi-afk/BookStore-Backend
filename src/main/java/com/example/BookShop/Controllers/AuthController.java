package com.example.BookShop.Controllers;
import com.example.BookShop.Service.AuthService;
import com.example.BookShop.Entities.User;
import com.example.BookShop.Repositories.UserRepository;
import com.example.BookShop.Security.CustomUserDetailsService;
import com.example.BookShop.Security.TokenResponse;
import com.example.BookShop.Security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ExecutionException;

@RequestMapping("/auth")
@CrossOrigin("*")
@RestController
public class AuthController {

    @Autowired  
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/register")
    ResponseEntity<?> registerUser(@RequestBody User user){
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if(existingUser.isPresent()){
            return new ResponseEntity<>("User already exist with email : "+user.getEmail(), HttpStatus.CONFLICT);
        }

        Random random = new Random();
        int num = random.nextInt(900000)+10000;
        user.setUserId(num);
        try{
            User createdUser = authService.saveUser(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        }catch (ExecutionException | InterruptedException | IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody User user){
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            try{
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
                );
            }catch (AuthenticationException e){
                return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
            }
            UserDetails userDetails;
            try{
                userDetails = customUserDetailsService.loadUserByUsername(user.getEmail());
            }catch (UsernameNotFoundException e){
                return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
            }
            String token = tokenService.generateToken(user.getEmail());
            User user1 = userRepository.findByEmail(user.getEmail()).orElse(null);
            return new ResponseEntity<>(new TokenResponse(token,user1),HttpStatus.OK);
        }
        return new ResponseEntity<>("User not found with email : "+user.getEmail(), HttpStatus.NOT_FOUND);
    }

}
