package com.example.moviecharactersapi.repository;

import com.example.moviecharactersapi.model.dbo.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Integer> {
}
