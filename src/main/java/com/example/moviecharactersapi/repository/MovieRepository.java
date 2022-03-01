package com.example.moviecharactersapi.repository;

import com.example.moviecharactersapi.model.dbo.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

}
