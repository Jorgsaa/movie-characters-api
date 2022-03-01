package com.example.moviecharactersapi.repository;

import com.example.moviecharactersapi.model.dbo.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FranchiseRepository extends JpaRepository<Franchise, Integer> {}
