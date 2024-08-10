package com.example.bookmarketservice.repository;

import com.example.bookmarketservice.entity.BookEntity;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<BookEntity, Integer> {
}
