package com.example.BookShop.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    private int bookId;

    private String title;

    private String author;

    private String genre;

    private double price;

    private int stock;

    private String isbn;

    private String description;

    @Lob
    @Column(length = 1000000)
    private byte[] image;

    private Date date;

}
