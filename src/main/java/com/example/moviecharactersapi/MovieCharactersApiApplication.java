package com.example.moviecharactersapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class MovieCharactersApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieCharactersApiApplication.class, args);
    }

}
