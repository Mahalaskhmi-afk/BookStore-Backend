package com.example.BookShop.Controllers;

import com.example.BookShop.Entities.User;
import com.example.BookShop.Repositories.UserRepository;
import com.example.BookShop.Service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/profile")
@CrossOrigin("*")
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileService profileService;

    @GetMapping("{userId}")
    public ResponseEntity<?> viewProfile(@PathVariable("userId") int userId){
        User existingUser = userRepository.findById(userId).orElse(null);
        if(existingUser != null){
            return new ResponseEntity<>(existingUser, HttpStatus.OK);
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.OK);
    }

    @PutMapping("{userId}")
    public ResponseEntity<?> editProfile(@PathVariable("userId") int userId,
                                         @RequestParam("firstName") String firstName,
                                         @RequestParam("lastName") String lastName,
                                         @RequestParam("email") String email,
                                         @RequestParam("password") String password,
                                         @RequestParam("gender") String gender,
                                         @RequestParam("address") String address,
                                         @RequestParam("city") String city,
                                         @RequestParam("state") String state,
                                         @RequestParam("country") String country,
                                         @RequestParam("pincode") int pinCode,
                                         @RequestParam("role") String role,
                                         @RequestPart("file")MultipartFile file
                                         ){
        try{
            User user = User.builder().
                    userId(userId).
                    firstName(firstName).
                    lastName(lastName).
                    email(email).
                    password(password).
                    gender(gender).
                    address(address).
                    city(city).
                    state(state).
                    country(country).
                    pinCode(pinCode).
                    role(role).
                    image(file.getBytes()).
                    build();
            User updatedUser = profileService.updateProfile(user);
            return new ResponseEntity<>(updatedUser, HttpStatus.ACCEPTED);
        }catch (IOException e ){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
