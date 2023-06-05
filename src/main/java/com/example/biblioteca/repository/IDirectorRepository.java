package com.example.biblioteca.repository;

import com.example.biblioteca.model.entity.Director;
import com.example.biblioteca.model.entity.Writer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDirectorRepository extends JpaRepository<Director,Long> {
    List<Director> findByNameContainingIgnoreCaseAndSurnamesContainingIgnoreCase(String name, String surnames);
    List<Director> findByNameContainingIgnoreCase(String name);
    List<Director> findBySurnamesContainingIgnoreCase(String surnames);

    List<Director> findAllByNameEqualsIgnoreCaseAndSurnamesEqualsIgnoreCase(String name,String surnames);

}
