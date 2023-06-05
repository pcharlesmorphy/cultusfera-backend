package com.example.biblioteca.service;

import com.example.biblioteca.model.entity.RecordCompany;

import java.util.List;
import java.util.Optional;

public interface IRecordCompanyService {
    List<RecordCompany> findAll ();
    Optional<RecordCompany> save(RecordCompany recordCompany);
    Boolean delete(Long id);
    Optional<RecordCompany> update (RecordCompany recordCompany);
    Optional<RecordCompany> findById (Long id);
}
