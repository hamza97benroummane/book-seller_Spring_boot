package com.hmz.spring_boot_book_seller.service;

import org.springframework.transaction.annotation.Transactional;

public interface IUserService {
    @Transactional
    void makeAdmin(String username);
}
