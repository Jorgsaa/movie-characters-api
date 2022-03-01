package com.example.moviecharactersapi.controller;

import com.example.moviecharactersapi.model.dbo.Franchise;
import com.example.moviecharactersapi.model.dto.Response;
import com.example.moviecharactersapi.repository.FranchiseRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

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

    @ApiOperation("Create new franchise")
    @SneakyThrows
    @PostMapping
    public ResponseEntity<Response<Franchise>> createFranchise(
            @RequestBody(required = false)
            Franchise franchise
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
    public ResponseEntity<Response<Franchise>> updateFranchise(
            @PathVariable Integer id,
            @RequestBody(required = false) Franchise franchise
    ) {
        if (franchise == null)
            return ResponseEntity.badRequest()
                    .body(new Response<>("Invalid franchise object supplied"));

        if (!franchises.existsById(id))
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response<>("Franchise with the specified id was not found"));

        franchise.setId(id);

        Franchise patchedFranchise = franchises.save(franchise);

        return ResponseEntity.accepted().body(new Response<>(patchedFranchise));
    }

    @ApiOperation("Delete a franchise by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Boolean>> deleteFranchise(@PathVariable Integer id) {
        franchises.deleteById(id);

        return ResponseEntity.accepted().body(new Response<>(true));
    }
}
