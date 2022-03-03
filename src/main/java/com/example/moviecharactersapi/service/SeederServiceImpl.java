package com.example.moviecharactersapi.service;

import com.example.moviecharactersapi.model.dbo.Character;
import com.example.moviecharactersapi.model.dbo.Franchise;
import com.example.moviecharactersapi.model.dbo.Movie;
import com.example.moviecharactersapi.repository.CharacterRepository;
import com.example.moviecharactersapi.repository.FranchiseRepository;
import com.example.moviecharactersapi.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SeederServiceImpl implements SeederService {

    private final FranchiseRepository franchises;
    private final MovieRepository movies;
    private final CharacterRepository characters;
    public static final Franchise THE_LORD_OF_THE_RINGS = Franchise.builder()
            .name("The Lord of the Rings")
            .description("Heroes set forth to save their world from evil")
            .build();
    public static final Franchise JAMES_BOND = Franchise.builder()
            .name("James Bond")
            .description("Secret agent having sex before saving the day")
            .build();
    public static final Franchise STAR_WARS = Franchise.builder()
            .name("Star Wars")
            .description("A group of Jedis and alies vs the Galactic Empire")
            .build();
    public static final Movie.MovieBuilder THE_FELLOWSHIP_OF_THE_RING = Movie.builder()
            .title("The Fellowship of the Ring")
            .releaseYear(2001)
            .genre("Fantasy")
            .director("Peter Jackson")
            .trailerURL("https://www.youtube.com/watch?v=V75dMMIW2B4")
            .pictureURL("https://m.media-amazon.com/images/M/MV5BN2EyZjM3NzUtNWUzMi00MTgxLWI0NTctMzY4M2VlOTdjZWRiXkEyXkFqcGdeQXVyNDUzOTQ5MjY@._V1_.jpg");
    public static final Movie.MovieBuilder THE_TWO_TOWERS = Movie.builder()
            .title("The Two Towers")
            .releaseYear(2002)
            .genre("Fantasy")
            .director("Peter Jackson")
            .trailerURL("https://www.youtube.com/watch?v=hYcw5ksV8YQ")
            .pictureURL("https://m.media-amazon.com/images/M/MV5BZGMxZTdjZmYtMmE2Ni00ZTdkLWI5NTgtNjlmMjBiNzU2MmI5XkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_.jpg");
    public static final Movie.MovieBuilder THE_RETURN_OF_THE_KING = Movie.builder()
            .title("The Return of the King")
            .releaseYear(2003)
            .genre("Fantasy")
            .director("Peter Jackson")
            .trailerURL("https://www.youtube.com/watch?v=r5X-hFf6Bwo")
            .pictureURL("https://m.media-amazon.com/images/M/MV5BNzA5ZDNlZWMtM2NhNS00NDJjLTk4NDItYTRmY2EwMWZlMTY3XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_FMjpg_UX1000_.jpg");
    public static final Character FRODO = Character.builder()
            .firstName("Frodo")
            .lastName("Baggins")
            .gender("Male")
            .pictureURL("https://upload.wikimedia.org/wikipedia/en/thumb/4/4e/Elijah_Wood_as_Frodo_Baggins.png/170px-Elijah_Wood_as_Frodo_Baggins.png")
            .build();
    public static final Character GANDALF = Character.builder()
            .firstName("Gandalf")
            .gender("Male")
            .pictureURL("https://upload.wikimedia.org/wikipedia/en/thumb/e/e9/Gandalf600ppx.jpg/170px-Gandalf600ppx.jpg")
            .build();
    public static final Character ARAGORN = Character.builder()
            .firstName("Aragorn")
            .lastName("II")
            .gender("Male")
            .pictureURL("https://upload.wikimedia.org/wikipedia/en/thumb/3/35/Aragorn300ppx.png/170px-Aragorn300ppx.png")
            .build();
    public static final Character ARWEN = Character.builder()
            .firstName("Arwen")
            .lastName("Undómiel")
            .gender("Female")
            .pictureURL("https://upload.wikimedia.org/wikipedia/commons/thumb/5/5f/Arwen_by_Anna_Kulisz.jpg/220px-Arwen_by_Anna_Kulisz.jpg")
            .build();

    @Override
    public void seedFranchises() {

        List<Franchise> franchiseList = List.of(
                THE_LORD_OF_THE_RINGS,
                JAMES_BOND,
                STAR_WARS
        );

        franchises.saveAll(franchiseList);
    }

    @Override
    public void seedMovies() {

        List<Movie> movieList = List.of(
                THE_FELLOWSHIP_OF_THE_RING.characters(List.of(FRODO, ARAGORN)).build(),
                THE_TWO_TOWERS.characters(List.of(FRODO, GANDALF)).build(),
                THE_RETURN_OF_THE_KING.characters(List.of(ARWEN)).build()
        );

        movies.saveAll(movieList);
    }

    @Override
    public void seedCharacters() {
        List<Character> characterList = List.of(
                FRODO,
                GANDALF,
                ARAGORN,
                ARWEN
        );

        characters.saveAll(characterList);
    }

}
