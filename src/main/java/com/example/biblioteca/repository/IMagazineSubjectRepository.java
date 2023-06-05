package com.example.biblioteca.repository;

import com.example.biblioteca.model.entity.MagazineSubject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMagazineSubjectRepository extends JpaRepository<MagazineSubject,Long> {
}
