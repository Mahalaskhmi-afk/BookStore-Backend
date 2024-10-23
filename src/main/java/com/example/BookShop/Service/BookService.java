package com.example.BookShop.Service;

import com.example.BookShop.Entities.Book;
import com.example.BookShop.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book saveBook(Book book){
        return bookRepository.save(book);
    }
    public List<Book> getBooks(){
        return bookRepository.findAll();
    }
}
