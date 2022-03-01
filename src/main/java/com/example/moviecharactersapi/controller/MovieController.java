package com.example.moviecharactersapi.controller;

import com.example.moviecharactersapi.model.dbo.Movie;
import com.example.moviecharactersapi.model.dto.Response;
import com.example.moviecharactersapi.repository.MovieRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/movie")
public class MovieController {

    private final MovieRepository movies;

    public MovieController(MovieRepository movies) {
        this.movies = movies;
    }

    @ApiOperation("Find a movie by id")
    @GetMapping("{id}")
    public ResponseEntity<Response<Movie>> findMovieById(@PathVariable Integer id) {
        // If found, return 200 response with movie
        return movies.findById(id).map(movie -> ResponseEntity.ok(new Response<>(movie)))
                // Else return 404 with error message
                .orElse(ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new Response<>("Movie with the specified id was not found")));
    }

    @ApiOperation("Add a movie")
    @SneakyThrows
    @PostMapping
    public ResponseEntity<Response<Movie>> save(
            @RequestBody(required = false) Movie movie
    ) {
        if (movie == null)
            return ResponseEntity.badRequest()
                    .body(new Response<>("Invalid movie object supplied"));

        Movie savedMovie = movies.save(movie);
        URI uri = new URI("/api/v1/movie/" + savedMovie.getId());
        return ResponseEntity.created(uri).body(new Response<>(savedMovie));
    }

    @ApiOperation("Update a movie by id")
    @PatchMapping("{id}")
    public ResponseEntity<Response<Movie>> update(
            @PathVariable Integer id,
            @RequestBody(required = false) Movie movie
    ) {
        if (movie == null)
            return ResponseEntity.badRequest()
                    .body(new Response<>("Invalid movie object supplied"));

        if (!movies.existsById(id))
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response<>("Movie with the specified id was not found"));

        movie.setId(id);

        Movie patchedMovie = movies.save(movie);

        return ResponseEntity.accepted().body(new Response<>(patchedMovie));
    }

    @ApiOperation("Delete a movie by id")
    @DeleteMapping("{id}")
    public ResponseEntity<Response<Boolean>> update(@PathVariable Integer id) {
        if (!movies.existsById(id))
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response<>("Movie with the specified id was not found"));

        movies.deleteById(id);

        return ResponseEntity.accepted().body(new Response<>(true));
    }

}
