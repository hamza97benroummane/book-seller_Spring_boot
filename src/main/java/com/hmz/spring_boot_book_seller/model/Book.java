package com.hmz.spring_boot_book_seller.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "book")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title",nullable = false, unique = true , length = 100)
    private String title;

    @Column(name = "description",nullable = false)
    private String description;

    @Column(name = "author",nullable = false, unique = true , length = 100)
    private String author;

    @Column(name = "price",nullable = false)
    private Double price;

    @Column(name = "createTime",nullable = false)
    private LocalDateTime createTime;
}
