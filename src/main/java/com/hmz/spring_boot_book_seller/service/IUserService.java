package com.hmz.spring_boot_book_seller.service;

import com.hmz.spring_boot_book_seller.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface IUserService {
    Optional<User> findByUsername(String username);

    @Transactional
    void makeAdmin(String username);

    User saveUser(User user);
}
