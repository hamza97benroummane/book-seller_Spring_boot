package com.hmz.spring_boot_book_seller.service;

import com.hmz.spring_boot_book_seller.model.User;

public interface IAuthenticationService {
    User signInAndReturnJWT(User signInRequest);
}
