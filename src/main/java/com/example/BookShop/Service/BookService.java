package com.example.BookShop.Service;

import com.example.BookShop.Entities.Book;
import com.example.BookShop.Entities.Dto.BookDto;
import com.example.BookShop.Repositories.BookRepository;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Random;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book saveBook(BookDto bookDto) throws IOException {

        Book book = new Book();

        book.setBookId(bookDto.getBookId());
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setGenre(bookDto.getGenre());
        book.setPrice(bookDto.getPrice());
        book.setStock(bookDto.getStock());
        book.setIsbn(bookDto.getIsbn());
        book.setDescription(bookDto.getDescription());
        book.setImage(bookDto.getImage().getBytes());

        book.setDate(bookDto.getDate());

        return bookRepository.save(book);
    }


    public List<Book> getBooks(){
        return bookRepository.findAll();
    }
}
