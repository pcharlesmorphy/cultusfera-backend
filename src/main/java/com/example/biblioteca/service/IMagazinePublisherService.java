package com.example.biblioteca.service;


import com.example.biblioteca.model.entity.MagazinePublisher;

import java.util.List;
import java.util.Optional;

public interface IMagazinePublisherService {
    List<MagazinePublisher> findAll ();
    Optional<MagazinePublisher> save(MagazinePublisher publisher);
    Boolean delete(Long id);
    Optional<MagazinePublisher> update (MagazinePublisher publisher);
    Optional<MagazinePublisher> findById (Long id);

}
