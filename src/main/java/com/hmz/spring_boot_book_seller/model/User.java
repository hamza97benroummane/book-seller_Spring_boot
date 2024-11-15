package com.hmz.spring_boot_book_seller.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username",nullable = false, unique = true ,length = 100)
    private String username;

    @Column(name = "password",nullable = false, unique = true , length = 100)
    private String password;

    @Column(name = "name",nullable = false, unique = true , length = 100)
    private String name;

    @Column(name = "createTime",nullable = false)
    private LocalDateTime createTime;


    @Enumerated(EnumType.STRING)
    @Column(name = "role",nullable = false)
    private Role role;

    @Transient
    private String token;


}
