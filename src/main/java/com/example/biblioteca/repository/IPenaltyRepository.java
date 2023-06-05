package com.example.biblioteca.repository;

import com.example.biblioteca.model.entity.Penalty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPenaltyRepository extends JpaRepository<Penalty,Long> {
}
