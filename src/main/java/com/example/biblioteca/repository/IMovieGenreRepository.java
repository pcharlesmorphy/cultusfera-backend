package com.example.biblioteca.repository;

import com.example.biblioteca.model.entity.MovieGenre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMovieGenreRepository extends JpaRepository<MovieGenre,Long> {
}
