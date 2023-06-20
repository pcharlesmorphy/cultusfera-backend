package com.example.biblioteca.repository;

import com.example.biblioteca.model.entity.Musician;
import com.example.biblioteca.model.entity.Writer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IMusicianRepository extends JpaRepository<Musician,Long> {
    List<Musician> findByNameContainingIgnoreCaseAndSurnamesContainingIgnoreCase(String name, String surnames);
    List<Musician> findByNameContainingIgnoreCase(String name);
    List<Musician> findBySurnamesContainingIgnoreCase(String surnames);

    List<Musician> findByNameEqualsIgnoreCaseAndSurnamesEqualsIgnoreCase (String name, String surnames);
}
