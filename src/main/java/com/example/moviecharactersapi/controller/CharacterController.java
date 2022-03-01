package com.example.moviecharactersapi.controller;

import com.example.moviecharactersapi.model.dto.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/character")
public class CharacterController {
    @GetMapping
    public ResponseEntity<Response> test() {
        return ResponseEntity.ok(new Response("Hello!"));
    }


}
