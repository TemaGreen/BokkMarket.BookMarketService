package com.example.bookmarketservice.service;

import com.example.bookmarketservice.data.Book;

import java.util.List;

public interface BookService {

    Book getBookById(Integer id);

    List<Book> getAllBooks();

    void addBook(Book book);
}
