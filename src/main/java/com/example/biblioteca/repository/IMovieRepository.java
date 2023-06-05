package com.example.biblioteca.repository;

import com.example.biblioteca.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IMovieRepository extends JpaRepository<Movie,Long> {
    List<Movie> findAllByActors (Actor actor);

    List<Movie> findByTitleContainsIgnoreCase(String title);
    List<Movie> findAllByTitleEqualsIgnoreCase(String title);
    List<Movie> findAllByDirector(Director director);
    List<Movie> findAllByGenre(MovieGenre genre);
    List<Movie> findAllByLanguage (Language language);

}
