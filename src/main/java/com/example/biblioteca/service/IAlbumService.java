package com.example.biblioteca.service;

import com.example.biblioteca.model.entity.*;

import java.util.List;
import java.util.Optional;

public interface IAlbumService {

    List<Album> findAllByGenre(MusicGenre genre);
    List<Album> findAllByMusician (Musician musician);

    List<Album> findAll ();
    Optional<Album> save(Album album);
    Boolean delete(Long id);
    Optional<Album> update (Album album);
    Optional<Album> findById (Long id);

    List<Album> findByTitleContainsIgnoreCase(String title);

    List<Album> findAllByMusicians(Musician musician);
    List<Album> findAlbumByMusician(String name, String surnames);
    List<Album> findAlbumByGenre (MusicGenre genre);

    List<Album> findAllByRecordCompany (RecordCompany company);

}
