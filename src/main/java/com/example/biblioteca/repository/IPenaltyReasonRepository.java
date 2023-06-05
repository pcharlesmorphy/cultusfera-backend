package com.example.biblioteca.repository;

import com.example.biblioteca.model.entity.PenaltyReason;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPenaltyReasonRepository extends JpaRepository<PenaltyReason,Long> {
}
