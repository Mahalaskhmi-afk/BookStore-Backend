package com.example.BookShop.Controllers;

import com.example.BookShop.Entities.Book;
import com.example.BookShop.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/book")
@CrossOrigin("*")
public class BookController {

    @Autowired
    private BookService bookService;


    @PostMapping("/add")
    public ResponseEntity<?> saveBook(@RequestParam("title") String title,
                                      @RequestParam("author") String author,
                                      @RequestParam("genre") String genre,
                                      @RequestParam("price") double price,
                                      @RequestParam("stock") int stock,
                                      @RequestParam("isbn") String isbn,
                                      @RequestParam("description") String description,
                                      @RequestPart("file") MultipartFile file
                                      ) throws IOException{

        try{
            Random random = new Random();
            Book book = Book.builder().
                    bookId(random.nextInt(9000)+10000).
                    title(title).
                    author(author).
                    genre(genre).
                    price(price).
                    stock(stock).
                    isbn(isbn).
                    description(description).
                    date(new Date()).
                    image(file.getBytes()).build();

            Book createdBook = bookService.saveBook(book);
            return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
        }catch (Exception e){
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
