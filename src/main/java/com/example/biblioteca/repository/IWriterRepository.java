package com.example.biblioteca.repository;

import com.example.biblioteca.model.entity.Writer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IWriterRepository extends JpaRepository<Writer,Long> {

    List<Writer> findByNameContainingIgnoreCaseAndSurnamesContainingIgnoreCase(String name, String surnames);
    List<Writer> findByNameContainingIgnoreCase(String name);
    List<Writer> findBySurnamesContainingIgnoreCase(String surnames);

    List<Writer> findByNameEqualsIgnoreCaseAndSurnamesEqualsIgnoreCase(String name, String surnames);
}
