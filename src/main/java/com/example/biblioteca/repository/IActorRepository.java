package com.example.biblioteca.repository;

import com.example.biblioteca.model.entity.Actor;
import com.example.biblioteca.model.entity.Director;
import com.example.biblioteca.model.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IActorRepository extends JpaRepository <Actor,Long> {
    List<Actor> findAllByMovies(Movie movie);
    List<Actor> findByNameContainingIgnoreCaseAndSurnamesContainingIgnoreCase(String name, String surnames);
    List<Actor> findByNameContainingIgnoreCase(String name);
    List<Actor> findBySurnamesContainingIgnoreCase(String surnames);
    List<Actor> findAllByNameEqualsIgnoreCaseAndSurnamesEqualsIgnoreCase (String name, String surnames);
}
