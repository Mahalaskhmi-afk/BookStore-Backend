package com.example.BookShop.Entities.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDto {

    private int bookId;

    private String title;

    private String author;

    private String genre;

    private double price;

    private int stock;

    private String isbn;

    private String description;

    private byte[] returnedImage;

    private MultipartFile image;

    private Date date;

}
