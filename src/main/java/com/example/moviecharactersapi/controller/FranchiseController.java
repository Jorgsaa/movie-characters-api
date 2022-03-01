package com.example.moviecharactersapi.controller;

import com.example.moviecharactersapi.model.dbo.Franchise;
import com.example.moviecharactersapi.model.dbo.Movie;
import com.example.moviecharactersapi.model.dto.Response;
import com.example.moviecharactersapi.repository.FranchiseRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Franchise")
@RequestMapping("/api/v1/franchise")
public class FranchiseController {
    private final FranchiseRepository franchises;

    public FranchiseController(FranchiseRepository franchises) {
        this.franchises = franchises;
    }

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
}
