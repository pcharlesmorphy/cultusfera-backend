package com.example.biblioteca.repository;

import com.example.biblioteca.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAlbumRepository extends JpaRepository<Album,Long> {

    List<Album> findAllByGenre (MusicGenre genre);
    List<Album> findAllByMusician (Musician musician);
    List<Album> findByTitleContainsIgnoreCase (String title);
    List<Album> findAllByTitleEqualsIgnoreCase (String title);

    List<Album> findAllByRecordCompany (RecordCompany company);

}
