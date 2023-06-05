package com.example.biblioteca.repository;

import com.example.biblioteca.model.entity.MusicGenre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMusicGenreRepository extends JpaRepository<MusicGenre,Long> {
}
