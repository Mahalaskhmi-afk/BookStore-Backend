package com.example.BookShop.Controllers;

import com.example.BookShop.Entities.Book;
import com.example.BookShop.Entities.Dto.BookDto;
import com.example.BookShop.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/book")
@CrossOrigin("*")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<?> addBook(@ModelAttribute BookDto bookDto)  {
        try{
            bookDto.setReturnedImage(bookDto.getImage().getBytes());
            Random random = new Random();
            int num = random.nextInt(9000)+1000;
            bookDto.setBookId(num);
            bookService.saveBook(bookDto);
            return new ResponseEntity<>(bookDto, HttpStatus.CREATED);
        }catch (IOException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("")
    public ResponseEntity<?> getBook(){
        try{
            List<Book> books = bookService.getBooks();
            return new ResponseEntity<>(books,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
