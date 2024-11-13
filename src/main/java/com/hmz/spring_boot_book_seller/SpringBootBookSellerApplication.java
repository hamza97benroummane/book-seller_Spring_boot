package com.hmz.spring_boot_book_seller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringBootBookSellerApplication {


	public static void main(String[] args) {
		SpringApplication.run(SpringBootBookSellerApplication.class, args);
	}

}
