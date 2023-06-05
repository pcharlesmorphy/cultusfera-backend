package com.example.biblioteca.service;

import com.example.biblioteca.model.entity.*;

import java.util.List;
import java.util.Optional;

public interface IMagazineService {
    List<Magazine> findAll ();
    Optional<Magazine> save(Magazine Magazine);
    Boolean delete(Long id);
    Optional<Magazine> update (Magazine Magazine);
    Optional<Magazine> findById (Long id);
    List<Magazine> findBySubject (MagazineSubject subject);
    List<Magazine> findByTitleContainsIgnoreCase(String title);

    List<Magazine> findByLanguage (Language language);

    List<Magazine> findAllByPublisher (MagazinePublisher publisher);

}
