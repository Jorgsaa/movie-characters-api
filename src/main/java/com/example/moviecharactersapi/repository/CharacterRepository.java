package com.example.moviecharactersapi.repository;

import com.example.moviecharactersapi.model.dbo.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Integer> {
    @Query("""
            SELECT DISTINCT c FROM Character c
            INNER JOIN c.movies movie
            INNER JOIN movie.franchise franchise
            WHERE franchise.id = ?1
            """)
    List<Character> findCharactersByFranchise(Integer franchiseId);
}
