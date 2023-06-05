package com.example.biblioteca.repository;

import com.example.biblioteca.model.entity.LiteraryGenre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILiteraryGenreRepository extends JpaRepository<LiteraryGenre,Long> {
}
