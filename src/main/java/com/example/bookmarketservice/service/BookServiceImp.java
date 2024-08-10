package com.example.bookmarketservice.service;

import com.example.bookmarketservice.data.Book;
import com.example.bookmarketservice.entity.BookEntity;
import com.example.bookmarketservice.repository.BookRepository;
import exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImp implements BookService{

    private BookRepository bookRepository;

    @Override
    public Book getBookById(Integer id) {
        BookEntity bookEntity = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found"));
        return new Book(
                bookEntity.getId(),
                bookEntity.getAuthor(),
                bookEntity.getTitle(),
                bookEntity.getPrice()
        );
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = StreamSupport.stream(bookRepository.findAll().spliterator(), false)
                .map(e -> new Book(
                        e.getId(),
                        e.getAuthor(),
                        e.getTitle(),
                        e.getPrice()))
                .toList();
        return books;
    }

    @Override
    public void addBook(Book book) {
        BookEntity bookEntity = new BookEntity(
                book.getAuthor(),
                book.getTitle(),
                book.getPrice());

        bookRepository.save(bookEntity);
    }

    public BookServiceImp(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookServiceImp() {

    }
}
