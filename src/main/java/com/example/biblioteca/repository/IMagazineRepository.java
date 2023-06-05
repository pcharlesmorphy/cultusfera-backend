package com.example.biblioteca.repository;

import com.example.biblioteca.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IMagazineRepository extends JpaRepository<Magazine,Long> {
    List<Magazine> findAllBySubject (MagazineSubject subject);
    List<Magazine> findByTitleContainsIgnoreCase (String title);
    List<Magazine> findAllByTitleEqualsIgnoreCase (String title);
    List<Magazine> findAllByLanguage (Language language);

    List<Magazine> findAllByPublisher (MagazinePublisher publisher);
}
