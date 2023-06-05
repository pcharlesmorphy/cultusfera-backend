package com.example.biblioteca.service;

import com.example.biblioteca.model.entity.MagazineSubject;

import java.util.List;
import java.util.Optional;

public interface IMagazineSubjectService {

    List<MagazineSubject> findAll ();
    Optional<MagazineSubject> findById (Long id);

}
