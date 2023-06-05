package com.example.biblioteca.repository;

import com.example.biblioteca.model.entity.Magazine;
import com.example.biblioteca.model.entity.MagazinePublisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMagazinePublisherRepository extends JpaRepository<MagazinePublisher,Long> {
    MagazinePublisher findByNameEqualsIgnoreCase (String name);
}
