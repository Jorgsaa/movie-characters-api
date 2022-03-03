package com.example.moviecharactersapi.model.dbo;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

}
