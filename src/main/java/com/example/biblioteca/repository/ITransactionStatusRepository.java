package com.example.biblioteca.repository;

import com.example.biblioteca.model.entity.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ITransactionStatusRepository extends JpaRepository<TransactionStatus,Long> {
    Optional<TransactionStatus> getByType (String type);
}
