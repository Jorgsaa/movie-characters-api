package com.example.moviecharactersapi.controller;

import com.example.moviecharactersapi.model.dbo.Franchise;
import com.example.moviecharactersapi.model.dbo.Movie;
import com.example.moviecharactersapi.model.dto.Response;
import com.example.moviecharactersapi.repository.FranchiseRepository;
import com.example.moviecharactersapi.repository.MovieRepository;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/franchise")
public class FranchiseController {

    private final FranchiseRepository franchises;
    private final MovieRepository movies;

    @ApiOperation("Find a franchise by id")
    @GetMapping("/{id}")
    public ResponseEntity<Response<Franchise>> findFranchiseById(@PathVariable Integer id) {
        // If found, return 200 response with franchise
        return franchises.findById(id).map(franchise -> ResponseEntity.ok(new Response<>(franchise)))
                // Else return 404 with error message
                .orElse(ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new Response<>("Franchise with the specified id was not found")));
    }

    @ApiOperation("Find all franchises")
    @GetMapping
    public ResponseEntity<Response<List<Franchise>>> findAllFranchises() {
        List<Franchise> franchiseList = franchises.findAll();

        if (franchiseList.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new Response<>("No franchises found"));
        }

        return ResponseEntity.ok(new Response<>(franchiseList));
    }

    @ApiOperation("Create new franchise")
    @SneakyThrows
    @PostMapping
    public ResponseEntity<Response<Franchise>> saveFranchise(
            @RequestBody(required = false) Franchise franchise
    ) {
        if (franchise == null) {
            return ResponseEntity.badRequest()
                    .body(new Response<>("Invalid franchise object supplied"));
        }

        // Save franchise and return URI of the saved franchise
        Franchise savedFranchise = franchises.save(franchise);
        URI uri = new URI("/api/v1/franchise/" + savedFranchise.getId());
        return ResponseEntity.created(uri).body(new Response<>(savedFranchise));
    }

    @ApiOperation("Update a franchise by id")
    @PatchMapping("/{id}")
    public ResponseEntity<Response<Franchise>> updateFranchiseById(
            @PathVariable Integer id,
            @RequestBody(required = false) Franchise franchise
    ) {
        if (franchise == null) {
            return ResponseEntity.badRequest()
                    .body(new Response<>("Invalid franchise object supplied"));
        }

        if (!franchises.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response<>("Franchise with the specified id was not found"));
        }

        franchise.setId(id);

        Franchise patchedFranchise = franchises.save(franchise);

        return ResponseEntity.accepted().body(new Response<>(patchedFranchise));
    }

    @ApiOperation("Delete a franchise by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Boolean>> deleteFranchiseById(@PathVariable Integer id) {
        if (!franchises.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response<>("Franchise with the specified id was not found"));
        }

        franchises.deleteById(id);

        return ResponseEntity.accepted().body(new Response<>(true));
    }

    @ApiOperation("Find movies by franchise id")
    @GetMapping("/{id}/movies")
    public ResponseEntity<Response<List<Movie>>> findMoviesByFranchiseId(@PathVariable Integer id) {
        if(!franchises.existsById(id)) {
            // Movie not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response<>("Franchise with the specified id was not found"));
        }

        return ResponseEntity.ok(new Response<>(franchises.findById(id).get().getMovies()));
    }

    @ApiOperation("Update movies related to a franchise, by id")
    @PatchMapping("{id}/movies")
    public ResponseEntity<Response<Franchise>> updateFranchiseMoviesById(
            @PathVariable Integer id,
            @RequestBody Set<Movie> movies
    ) {
        if (franchises.findById(id).isPresent()) {
            Franchise franchise = franchises.findById(id).get();
            
            return ResponseEntity
                    .accepted()
                    .body(new Response<>("Franchise with the specified id was found"));
        }
        
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new Response<>("Franchise with the specified id was not found"));
    }
}
