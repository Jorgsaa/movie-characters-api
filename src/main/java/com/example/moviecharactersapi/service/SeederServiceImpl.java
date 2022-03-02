package com.example.moviecharactersapi.service;

import com.example.moviecharactersapi.model.dbo.Character;
import com.example.moviecharactersapi.model.dbo.Franchise;
import com.example.moviecharactersapi.model.dbo.Movie;
import com.example.moviecharactersapi.repository.FranchiseRepository;
import com.example.moviecharactersapi.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeederServiceImpl implements SeederService {

    private final FranchiseRepository franchises;
    private final MovieRepository movies;

    public SeederServiceImpl(FranchiseRepository franchises, MovieRepository movies) {
        this.franchises = franchises;
        this.movies = movies;
    }

    @Override
    public void seedFranchises() {

        List<Franchise> franchiseList = List.of(
                Franchise.builder()
                        .id(1)
                        .name("The Lord of the Rings")
                        .description("Heroes set forth to save their world from evil")
                        .build(),

                Franchise.builder()
                        .id(2)
                        .name("James Bond")
                        .description("Secret agent having sex before saving the day")
                        .build(),

                Franchise.builder()
                        .id(3)
                        .name("Star Wars")
                        .description("A group of Jedis and alies vs the Galactic Empire")
                        .build()
        );

        franchises.saveAll(franchiseList);
    }

    @Override
    public void seedMovies() {

        List<Movie> movieList = List.of(
                Movie.builder()
                        .id(1)
                        .title("The Fellowship of the Ring")
                        .releaseYear(2001)
                        .genre("Fantasy")
                        .director("Peter Jackson")
                        .trailerURL("https://www.youtube.com/watch?v=V75dMMIW2B4")
                        .pictureURL("https://m.media-amazon.com/images/M/MV5BN2EyZjM3NzUtNWUzMi00MTgxLWI0NTctMzY4M2VlOTdjZWRiXkEyXkFqcGdeQXVyNDUzOTQ5MjY@._V1_.jpg")
                        .build(),

                Movie.builder()
                        .id(2)
                        .title("The Two Towers")
                        .releaseYear(2002)
                        .genre("Fantasy")
                        .director("Peter Jackson")
                        .trailerURL("https://www.youtube.com/watch?v=hYcw5ksV8YQ")
                        .pictureURL("https://m.media-amazon.com/images/M/MV5BZGMxZTdjZmYtMmE2Ni00ZTdkLWI5NTgtNjlmMjBiNzU2MmI5XkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_.jpg")
                        .build(),

                Movie.builder()
                        .id(3)
                        .title("The Return of the King")
                        .releaseYear(2003)
                        .genre("Fantasy")
                        .director("Peter Jackson")
                        .trailerURL("https://www.youtube.com/watch?v=r5X-hFf6Bwo")
                        .pictureURL("https://m.media-amazon.com/images/M/MV5BNzA5ZDNlZWMtM2NhNS00NDJjLTk4NDItYTRmY2EwMWZlMTY3XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_FMjpg_UX1000_.jpg")
                        .build()
        );

        movies.saveAll(movieList);
    }

    @Override
    public void seedCharacters() {
        List<Character> characterList = List.of(
                Character.builder()
                        .id(1)
                        .firstName("Frodo")
                        .lastName("Baggins")
                        .gender("Male")
                        .pictureURL("https://upload.wikimedia.org/wikipedia/en/thumb/4/4e/Elijah_Wood_as_Frodo_Baggins.png/170px-Elijah_Wood_as_Frodo_Baggins.png")
                        .build(),

                Character.builder()
                        .id(2)
                        .firstName("Gandalf")
                        .gender("Male")
                        .pictureURL("https://upload.wikimedia.org/wikipedia/en/thumb/e/e9/Gandalf600ppx.jpg/170px-Gandalf600ppx.jpg")
                        .build(),

                Character.builder()
                        .id(3)
                        .firstName("Aragorn")
                        .lastName("II")
                        .gender("Male")
                        .pictureURL("https://upload.wikimedia.org/wikipedia/en/thumb/3/35/Aragorn300ppx.png/170px-Aragorn300ppx.png")
                        .build(),

                Character.builder()
                        .id(3)
                        .firstName("Arwen")
                        .lastName("Undómiel")
                        .gender("Female")
                        .pictureURL("https://upload.wikimedia.org/wikipedia/commons/thumb/5/5f/Arwen_by_Anna_Kulisz.jpg/220px-Arwen_by_Anna_Kulisz.jpg")
                        .build()
        );

        //TODO: when repository is implemented and injected
        // characters.saveAll(characterList);
    }

}
