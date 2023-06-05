package com.example.biblioteca.service;

import com.example.biblioteca.model.entity.Book;
import com.example.biblioteca.model.entity.Writer;

import java.util.List;
import java.util.Optional;

public interface IWriterService {
    List<Writer> findAll ();
    List<Writer> findByName(String name, String surnames);
    Optional<Writer> save(Writer writer);
    Boolean delete(Long id);
    Optional<Writer> update (Writer writer);
    Optional<Writer> findById (Long id);

}
