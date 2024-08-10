package com.example.bookmarketservice.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String author;

    private String title;

    private Double price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public BookEntity(Integer id, String name, String author, String title, Double price) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.title = title;
        this.price = price;
    }

    public BookEntity(String name, String author, String title, Double price) {
        this.name = name;
        this.author = author;
        this.title = title;
        this.price = price;
    }

    public BookEntity() {
    }
}
