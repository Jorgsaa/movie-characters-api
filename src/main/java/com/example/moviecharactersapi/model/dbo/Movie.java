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
public class Movie {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, length = 80)
    private String title;

    @Column(nullable = false, length = 25)
    private String genre;

    @Column(nullable = false)
    private Integer releaseYear;

    @Column(nullable = false, length = 50)
    private String director;

    @Column(length = 200)
    private String pictureURL;

    @Column(length = 200)
    private String trailerURL;

    @Singular
    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(name = "movie_characters",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "character_id", referencedColumnName = "id"))
    private Set<Character> characters = new java.util.LinkedHashSet<>();

    @JsonGetter
    public Set<NamedResourceDTO> characters() {
        return characters.stream().map(c -> {
            StringBuilder name = new StringBuilder(c.getFirstName());

            if (c.getLastName() != null)
                name.append(" ").append(c.getLastName());

            return new NamedResourceDTO(
                    c.getId(),
                    name.toString(),
                    "/api/v1/character/" + c.getId()
            );
        }).collect(Collectors.toSet());
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "franchise_id")
    private Franchise franchise;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonGetter
    public NamedResourceDTO franchise() {
        if (franchise != null)
            return new NamedResourceDTO(
                    franchise.getId(),
                    franchise.getName(),
                    "/api/v1/franchise/" + franchise.getId()
            );
        else
            return null;
    }

    public void removeCharacter(Character character) {
        characters.remove(character);
    }

}
