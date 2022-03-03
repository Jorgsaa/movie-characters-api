package com.example.moviecharactersapi.controller;

import com.example.moviecharactersapi.model.dbo.Character;
import com.example.moviecharactersapi.model.dbo.Movie;
import com.example.moviecharactersapi.model.dto.Response;
import com.example.moviecharactersapi.repository.MovieRepository;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/movie")
public class MovieController {

    private final MovieRepository movies;

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

    @ApiOperation("Find all movies")
    @GetMapping
    public ResponseEntity<Response<List<Movie>>> findAllMovies() {
        List<Movie> movieList = movies.findAll();

        if (movieList.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new Response<>("No movies found"));
        }

        return ResponseEntity.ok(new Response<>(movieList));
    }

    @ApiOperation("Add a movie")
    @SneakyThrows
    @PostMapping
    public ResponseEntity<Response<Movie>> saveMovie(
            @RequestBody(required = false) Movie movie
    ) {
        if (movie == null) {
            return ResponseEntity.badRequest()
                    .body(new Response<>("Invalid movie object supplied"));
        }

        // Save movie and return URI of the saved movie
        Movie savedMovie = movies.save(movie);
        URI uri = new URI("/api/v1/movie/" + savedMovie.getId());
        return ResponseEntity.created(uri).body(new Response<>(savedMovie));
    }

    @ApiOperation("Update a movie by id")
    @PatchMapping("{id}")
    public ResponseEntity<Response<Movie>> updateMovieById(
            @PathVariable Integer id,
            @RequestBody(required = false) Movie movie
    ) {
        if (movie == null) {
            return ResponseEntity.badRequest()
                    .body(new Response<>("Invalid movie object supplied"));
        }

        if (!movies.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response<>("Movie with the specified id was not found"));
        }

        movie.setId(id);

        Movie patchedMovie = movies.save(movie);

        return ResponseEntity.accepted().body(new Response<>(patchedMovie));
    }

    @ApiOperation("Delete a movie by id")
    @DeleteMapping("{id}")
    public ResponseEntity<Response<Boolean>> deleteMovieById(@PathVariable Integer id) {
        if (!movies.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response<>("Movie with the specified id was not found"));
        }

        movies.deleteById(id);

        return ResponseEntity.accepted().body(new Response<>(true));
    }

    @ApiOperation("Find characters by movie id")
    @GetMapping("{id}/characters")
    public ResponseEntity<Response<Set<Character>>> findMovieCharactersById(@PathVariable Integer id) {
        return movies.findById(id).map(
                movie -> ResponseEntity.ok(new Response<>(movie.getCharacters()))
        ).orElse(
                // Movie was not found
                ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new Response<>("Movie with the specified id was not found")));
    }

    @ApiOperation("Update characters by movie id")
    @PatchMapping("{id}/characters")
    public ResponseEntity<Response<Movie>> updateMovieCharactersById(
            @PathVariable Integer id,
            @RequestBody Set<Character> characters
    ) {
        return movies.findById(id).map(
                movie -> {
                    // Add the characters to the movie
                    movie.setCharacters(characters);

                    // Check for exception on saving characters with non-existing ids
                    try {
                        Movie patchedMovie = movies.save(movie);
                        return ResponseEntity.accepted().body(new Response<>(patchedMovie));
                    } catch (JpaObjectRetrievalFailureException e) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(new Response<Movie>("One or more characters added the movie was not found"));
                    }

                }
        ).orElse(
                // Movie was not found
                ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new Response<>("Movie with the specified id was not found"))
        );

    }

}
