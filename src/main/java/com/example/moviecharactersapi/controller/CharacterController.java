package com.example.moviecharactersapi.controller;

import com.example.moviecharactersapi.model.dbo.Character;
import com.example.moviecharactersapi.model.dto.Response;
import com.example.moviecharactersapi.repository.CharacterRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.diff.JsonDiff;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/character")
public class CharacterController {

    private final CharacterRepository characters;
    private ObjectMapper objectMapper = new ObjectMapper();

    @ApiOperation("Find a character by id")
    @GetMapping("{id}")
    public ResponseEntity<Response<Character>> findCharacterById(@PathVariable Integer id) {
        // If found, return 200 response with character
        return characters.findById(id).map(character -> ResponseEntity.ok(new Response<>(character)))
                // Else return 404 with error message
                .orElse(ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new Response<>("Character with the specified id was not found")));
    }

    @ApiOperation("Find all characters")
    @GetMapping
    public ResponseEntity<Response<List<Character>>> findAllCharacters() {
        List<Character> characterList = characters.findAll();

        if (characterList.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new Response<>("No characters found"));
        }

        return ResponseEntity.ok(new Response<>(characterList));
    }

    @ApiOperation("Add a character")
    @SneakyThrows
    @PostMapping
    public ResponseEntity<Response<Character>> saveCharacter(
            @RequestBody(required = false) Character character
    ) {
        if (character == null) {
            return ResponseEntity.badRequest()
                    .body(new Response<>("Invalid character object supplied"));
        }

        // Save character and return URI of the saved character
        Character savedCharacter = characters.save(character);
        URI uri = new URI("/api/v1/character/" + savedCharacter.getId());
        return ResponseEntity.created(uri).body(new Response<>(savedCharacter));
    }

    @ApiOperation("Update a character by id")
    @PatchMapping("{id}")
    public ResponseEntity<Response<Character>> updateCharacterById(
            @PathVariable Integer id,
            @RequestBody JsonNode patch
    ) {
        if (!characters.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response<>("Character with the specified id was not found"));
        }

        Character originalCharacter = characters.findById(id).get();

        Character characterPatched;
        try {
            JsonNode originalAsJson = objectMapper.valueToTree(originalCharacter);
            JsonPatch patchDiff = JsonDiff.asJsonPatch(originalAsJson, patch);
            characterPatched = applyPatchToCharacter(patchDiff, originalCharacter);
            characters.save(characterPatched);
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().body(new Response<>("Internal server error, failed to patch character"));
        }

        return ResponseEntity.accepted().body(new Response<>(characterPatched));
    }

    @ApiOperation("Delete a character by id")
    @DeleteMapping("{id}")
    public ResponseEntity<Response<Boolean>> deleteCharacterById(@PathVariable Integer id) {
        if (!characters.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response<>("Character with the specified id was not found"));
        }

        // Remove character from movie
        characters.findById(id).ifPresent(character -> character.getMovies().forEach(m -> m.removeCharacter(character)));

        characters.deleteById(id);

        return ResponseEntity.accepted().body(new Response<>(true));
    }

    private Character applyPatchToCharacter(JsonPatch patch, Character targetCharacter) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetCharacter, JsonNode.class));
        return objectMapper.treeToValue(patched, Character.class);
    }
}
