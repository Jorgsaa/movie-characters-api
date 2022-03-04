package com.example.moviecharactersapi.model.dbo;

import com.example.moviecharactersapi.model.dto.NamedResourceDTO;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Character {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String alias;

    @Column(nullable = false)
    private String gender;

    @Column
    private String pictureURL;

    @Singular
    @JsonIgnore
    @ManyToMany(mappedBy = "characters", cascade = CascadeType.PERSIST)
    private Set<Movie> movies = new java.util.LinkedHashSet<>();

    @JsonGetter
    public Set<NamedResourceDTO> movies() {
        return movies.stream().map(movie -> new NamedResourceDTO(
                movie.getId(),
                movie.getTitle(),
                "/api/v1/movie/" + movie.getId()
        )).collect(Collectors.toSet());
    }

}
