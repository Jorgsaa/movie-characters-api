package com.example.moviecharactersapi;

import com.example.moviecharactersapi.service.SeederService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class MovieCharactersApiApplication {

    public MovieCharactersApiApplication(SeederService seederService) {
        seederService.seedFranchises();
        seederService.seedMovies();
        seederService.seedCharacters();
    }

    public static void main(String[] args) {
        SpringApplication.run(MovieCharactersApiApplication.class, args);
    }

}
