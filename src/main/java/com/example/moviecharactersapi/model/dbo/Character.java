package com.example.moviecharactersapi.model.dbo;

import com.example.moviecharactersapi.model.dto.NamedResourceDTO;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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

    @Column(nullable = false, length = 40)
    private String firstName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(length = 40)
    private String lastName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(length = 40)
    private String alias;

    @Column(nullable = false, length = 15)
    private String gender;

    @Column(length = 200)
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
