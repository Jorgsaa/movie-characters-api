package com.example.moviecharactersapi.model.dbo;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Franchise {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, length = 40)
    private String name;

    @Column(length = 80)
    private String description;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "franchise_id")
    private List<Movie> movies;

    @PreRemove
    private void preRemove() {
        movies.forEach( movie -> movie.setFranchise(null));
    }

    @JsonGetter
    public List<String> movies() {
        return movies.stream().map(Movie::getTitle).collect(Collectors.toList());
    }
}
