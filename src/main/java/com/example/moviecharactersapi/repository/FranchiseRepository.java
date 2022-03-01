package com.example.moviecharactersapi.repository;

import com.example.moviecharactersapi.model.dbo.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FranchiseRepository extends JpaRepository<Franchise, Integer> {}
