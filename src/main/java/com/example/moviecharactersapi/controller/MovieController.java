package com.example.moviecharactersapi.controller;

import com.example.moviecharactersapi.model.dbo.Movie;
import com.example.moviecharactersapi.model.dto.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/movie")
public class MovieController {

    @GetMapping("{id}")
    public ResponseEntity<Response<Movie>> findMovieById(@PathVariable Integer id) {
        return ResponseEntity.ok(
                new Response<>(
                        Movie.builder()
                                .id(id)
                                .title("Test title")
                                .genre("Test genre")
                                .releaseYear(2022)
                                .director("Test director")
                                .trailerURL("https://www.youtube.com/watch?v=dQw4w9WgXcQ")
                                .build()
                )
        );
    }

}
