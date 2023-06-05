package com.example.biblioteca.repository;

import com.example.biblioteca.model.entity.Author;
import com.example.biblioteca.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface IAuthorRepository extends JpaRepository<Author,Long> {
    List<Author> findAllByRole(Role role);

}
