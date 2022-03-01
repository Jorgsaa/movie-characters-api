package com.example.moviecharactersapi.controller;

import com.example.moviecharactersapi.model.dbo.Character;
import com.example.moviecharactersapi.model.dbo.Movie;
import com.example.moviecharactersapi.model.dto.Response;
import com.example.moviecharactersapi.repository.CharacterRepository;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/character")
public class CharacterController {
    final CharacterRepository characters;

    public CharacterController(CharacterRepository characters) {
        this.characters = characters;
    }

    @ApiOperation("Find a character by id")
    @GetMapping("{id}")
    public ResponseEntity<Response<Character>> findCharacterById(@PathVariable Integer id) {
        return characters.findById(id).map(character -> ResponseEntity.ok(new Response<>(character)))
                // Else return 404 with error message
                .orElse(ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new Response<>("Character with the specified id was not found")));
    }

    @ApiOperation("Add a character")
    @SneakyThrows
    @PostMapping
    public ResponseEntity<Response<Character>> save(
            @RequestBody(required = false) Character character
    ) {
        if (character == null)
            return ResponseEntity.badRequest()
                    .body(new Response<>("Invalid character object supplied"));

        Character savedCharacter = characters.save(character);
        URI uri = new URI("/api/v1/character/" + savedCharacter.getId());
        return ResponseEntity.created(uri).body(new Response<>(savedCharacter));
    }

    @ApiOperation("Update a character by id")
    @PatchMapping("{id}")
    public ResponseEntity<Response<Character>> update(
            @PathVariable Integer id,
            @RequestBody(required = false) Character character
    ) {
        if (character == null)
            return ResponseEntity.badRequest()
                    .body(new Response<>("Invalid character object supplied"));

        if (!characters.existsById(id))
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response<>("Character with the specified id was not found"));

        character.setId(id);

        Character patchedCharacter = characters.save(character);

        return ResponseEntity.accepted().body(new Response<>(patchedCharacter));
    }

    @ApiOperation("Delete a character by id")
    @DeleteMapping("{id}")
    public ResponseEntity<Response<Boolean>> update(@PathVariable Integer id) {
        if (!characters.existsById(id))
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response<>("Character with the specified id was not found"));

        characters.deleteById(id);

        return ResponseEntity.accepted().body(new Response<>(true));
    }
}