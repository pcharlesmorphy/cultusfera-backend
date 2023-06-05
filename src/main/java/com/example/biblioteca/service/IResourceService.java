package com.example.biblioteca.service;

import com.example.biblioteca.model.entity.Book;
import com.example.biblioteca.model.entity.Resource;

import java.util.List;
import java.util.Optional;

public interface IResourceService {
    Resource save(Resource resource);

    List<Resource> findAll();
    List<Resource> findAllByTitleContainsIgnoreCase(String title);

    Optional<Resource> findResourceById (Long id);
}
