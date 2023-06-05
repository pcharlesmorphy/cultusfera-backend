package com.example.biblioteca.repository;

import com.example.biblioteca.model.entity.Copy;
import com.example.biblioteca.model.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IResourceRepository extends JpaRepository<Resource,Long> {

    List<Resource> findAllBy();
    List<Resource> findAllByTitleContainsIgnoreCase(String title);
    List<Resource> findAllByCopies (Copy copy);

 }
