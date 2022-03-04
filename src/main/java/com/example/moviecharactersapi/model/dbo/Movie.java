package com.example.moviecharactersapi.model.dbo;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Movie {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    private Integer releaseYear;

    @Column(nullable = false)
    private String director;

    @Column
    private String pictureURL;

    @Column
    private String trailerURL;

    @Singular
    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(name = "movie_characters",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "character_id", referencedColumnName = "id"))
    private Set<Character> characters = new java.util.LinkedHashSet<>();

    @JsonGetter
    public Set<String> characters() {
        return characters.stream().map(c -> {
            StringBuilder name = new StringBuilder(c.getFirstName());

            if (c.getLastName() != null)
                name.append(" ").append(c.getLastName());

            return name.toString();
        }).collect(Collectors.toSet());
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "franchise_id")
    private Franchise franchise;

    @JsonGetter
    public String franchise() {
        if(franchise != null)
            return franchise.getId().toString();
        else
            return null;
    }

    public void removeCharacter(Character character) {
        characters.remove(character);
    }
}
