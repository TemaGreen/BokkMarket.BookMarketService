package com.example.bookmarketservice.controller;

import com.example.bookmarketservice.data.Book;
import com.example.bookmarketservice.request.AddBookRequest;
import com.example.bookmarketservice.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookService bookService;

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Integer id){
        return bookService.getBookById(id);
    }

    @GetMapping
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @PostMapping
    public void addBook(@RequestBody AddBookRequest addBookRequest){
        bookService.addBook(new Book(
                addBookRequest.getName(),
                addBookRequest.getAuthor(),
                addBookRequest.getTitle(),
                addBookRequest.getPrice())
        );
    }

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    public BookController() {
    }
}
