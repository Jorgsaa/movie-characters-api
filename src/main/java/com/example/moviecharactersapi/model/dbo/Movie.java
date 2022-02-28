package com.example.moviecharactersapi.model.dbo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
public class Movie {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String title;

    @Column
    private String genre;

    @Column
    private Integer releaseYear;

    @Column
    private String director;

    @Column
    private String pictureURL;

    @Column
    private String trailerURL;

}
