package com.example.biblioteca.service;

import com.example.biblioteca.model.entity.Musician;
import com.example.biblioteca.model.entity.Writer;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IMusicianService {
    List<Musician> findAll ();
    List<Musician> findByName(String name, String surnames);
    Optional<Musician> save(Musician musician);
    Boolean delete(Long id);
    Optional<Musician> update (Musician musician);
    Optional<Musician> findById (Long id);
}
