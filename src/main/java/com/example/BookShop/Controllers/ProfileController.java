package com.example.BookShop.Controllers;

import com.example.BookShop.Entities.Dto.UserDto;
import com.example.BookShop.Entities.User;
import com.example.BookShop.Repositories.UserRepository;
import com.example.BookShop.Service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

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
                                         @ModelAttribute UserDto user
                                         ){
        try{
            user.setImage(user.getImage());
            User user1 = profileService.updateProfile(userId, user);
            return new ResponseEntity<>(user1, HttpStatus.ACCEPTED);
        }catch (IOException e ){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
