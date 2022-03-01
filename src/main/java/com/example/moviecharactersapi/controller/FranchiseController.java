package com.example.moviecharactersapi.controller;

import com.example.moviecharactersapi.repository.FranchiseRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/franchise")
public class FranchiseController {
    private final FranchiseRepository franchises;

    public FranchiseController(FranchiseRepository franchises) {
        this.franchises = franchises;
    }
}
