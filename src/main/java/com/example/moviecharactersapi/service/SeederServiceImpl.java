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
    public static final Character GANDALF = Character.builder()
            .firstName("Gandalf")
            .alias("Mithrandir")
            .gender("Male")
            .pictureURL("https://upload.wikimedia.org/wikipedia/en/thumb/e/e9/Gandalf600ppx.jpg/170px-Gandalf600ppx.jpg")
            .build();
    public static final Character BOROMIR = Character.builder()
            .firstName("Boromir")
            .gender("Male")
            .pictureURL("https://upload.wikimedia.org/wikipedia/en/thumb/9/9b/Vlcsnap-bormir.png/300px-Vlcsnap-bormir.png")
            .build();
    public static final Character THEODEN = Character.builder()
            .firstName("Théoden")
            .lastName("Ednew")
            .gender("Male")
            .pictureURL("https://upload.wikimedia.org/wikipedia/en/thumb/3/3a/Th%C3%A9oden600ppx.png/220px-Th%C3%A9oden600ppx.png")
            .build();

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
            .pictureURL("https://m.media-amazon.com/images/M/MV5BN2EyZjM3NzUtNWUzMi00MTgxLWI0NTctMzY4M2VlOTdjZWRiXkEyXkFqcGdeQXVyNDUzOTQ5MjY@._V1_.jpg")
            .franchise(THE_LORD_OF_THE_RINGS);
    public static final Movie.MovieBuilder THE_TWO_TOWERS = Movie.builder()
            .title("The Two Towers")
            .releaseYear(2002)
            .genre("Fantasy")
            .director("Peter Jackson")
            .trailerURL("https://www.youtube.com/watch?v=hYcw5ksV8YQ")
            .pictureURL("https://m.media-amazon.com/images/M/MV5BZGMxZTdjZmYtMmE2Ni00ZTdkLWI5NTgtNjlmMjBiNzU2MmI5XkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_.jpg")
            .franchise(THE_LORD_OF_THE_RINGS);
    public static final Movie.MovieBuilder THE_RETURN_OF_THE_KING = Movie.builder()
            .title("The Return of the King")
            .releaseYear(2003)
            .genre("Fantasy")
            .director("Peter Jackson")
            .trailerURL("https://www.youtube.com/watch?v=r5X-hFf6Bwo")
            .pictureURL("https://m.media-amazon.com/images/M/MV5BNzA5ZDNlZWMtM2NhNS00NDJjLTk4NDItYTRmY2EwMWZlMTY3XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_FMjpg_UX1000_.jpg")
            .franchise(THE_LORD_OF_THE_RINGS);
    public static final Movie.MovieBuilder GOLDFINGER = Movie.builder()
            .title("Goldfinger")
            .releaseYear(1964)
            .genre("Action")
            .director("Guy Hamilton")
            .trailerURL("https://www.youtube.com/watch?v=MA65V-oLKa8")
            .pictureURL("https://sfanytime-images-prod-http2.secure2.footprint.net/COVERM/COVERM_20982fb9-4bdc-421c-9c59-4ec37dc457ba_01.jpg?w=375&ar=0.692&fit=crop&fm=pjpg&s=581b523028cbcb8e01caf9acf357c2bf")
            .franchise(JAMES_BOND);
    public static final Movie.MovieBuilder RETURN_OF_THE_JEDI = Movie.builder()
            .title("Return of the Jedi")
            .releaseYear(1983)
            .genre("Sci-fi")
            .director("Richard Marquand")
            .trailerURL("https://www.youtube.com/watch?v=7L8p7_SLzvU")
            .pictureURL("https://sfanytime-images-prod-http2.secure2.footprint.net/COVERM/COVERM_c0f92d8e-90ee-4d89-98a9-d491d3827571_01.jpg?w=375&ar=0.692&fit=crop&fm=pjpg&s=2518eb87509fa4cfcdf757d389c08cac")
            .franchise(STAR_WARS);

    public static final Character FRODO = Character.builder()
            .firstName("Frodo")
            .lastName("Baggins")
            .gender("Male")
            .pictureURL("https://upload.wikimedia.org/wikipedia/en/thumb/4/4e/Elijah_Wood_as_Frodo_Baggins.png/170px-Elijah_Wood_as_Frodo_Baggins.png")
            .build();

    public static final Character JAMES_BOND_CHARACTER = Character.builder()
            .firstName("James")
            .lastName("Bond")
            .gender("Male")
            .pictureURL("https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/irish-actor-pierce-brosnan-as-james-bond-in-a-publicity-news-photo-138084872-1551742467.jpg?crop=1xw:1xh;center,top&resize=480:*")
            .build();
    public static final Character LUKE_SKYWALKER = Character.builder()
            .firstName("Luke")
            .lastName("Skywalker")
            .gender("Male")
            .pictureURL("https://static.wikia.nocookie.net/jedipedy/images/8/84/Lukeskywalker.jpg/revision/latest?cb=20150423151511&path-prefix=fy")
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
                THE_FELLOWSHIP_OF_THE_RING.characters(List.of(FRODO, GANDALF, BOROMIR))
                        .build(),
                THE_TWO_TOWERS.characters(List.of(FRODO, GANDALF, BOROMIR, THEODEN))
                        .build(),
                THE_RETURN_OF_THE_KING.characters(List.of(FRODO, GANDALF, THEODEN))
                        .build(),
                GOLDFINGER.characters(List.of(JAMES_BOND_CHARACTER))
                        .build(),
                RETURN_OF_THE_JEDI.characters(List.of(LUKE_SKYWALKER))
                        .build()
        );

        movies.saveAll(movieList);
    }

    @Override
    public void seedCharacters() {

        List<Character> characterList = List.of(
                FRODO,
                GANDALF,
                BOROMIR,
                THEODEN,
                LUKE_SKYWALKER,
                JAMES_BOND_CHARACTER
        );

        characters.saveAll(characterList);
    }

}
