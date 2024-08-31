package com.example.BookShop.Entities.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private int userId;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String gender;

    private String address;

    private String city;

    private String state;

    private String country;

    private int pinCode;

    private byte[] returnedImage;

    private MultipartFile image;

    private String role;

}
