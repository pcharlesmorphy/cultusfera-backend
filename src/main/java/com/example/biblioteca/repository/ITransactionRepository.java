package com.example.biblioteca.repository;

import com.example.biblioteca.model.entity.Copy;
import com.example.biblioteca.model.entity.Transaction;
import com.example.biblioteca.model.entity.TransactionStatus;
import com.example.biblioteca.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findAllByUser (User user);
    List<Transaction> findAllByCopy (Copy copy);

}
