package com.example.moviecharactersapi.service;

import com.example.moviecharactersapi.model.dbo.Movie;
import com.example.moviecharactersapi.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeederServiceImpl implements SeederService {

    private final MovieRepository movies;

    public SeederServiceImpl(MovieRepository movies) {
        this.movies = movies;
    }

    @Override
    public void seedFranchises() {

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

    }

}
