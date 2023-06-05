package com.example.biblioteca.service;

import com.example.biblioteca.model.entity.*;

import java.util.List;
import java.util.Optional;

public interface ICopyService {

    List<Copy> findAll ();
    Copy save(Copy copy);
    Boolean delete(Long id);
    Copy update (Copy copy);
    Optional<Copy> findCopyById (Long id);
    Optional<StatusCopy> findStatusById (Long id);

    List<Copy> findAllResourceCopies(Resource resource);
    List<Transaction> findCopyActiveTransactions (Copy copy);

    List<Transaction> findCopyHistoricTransactions (Copy copy);

}
