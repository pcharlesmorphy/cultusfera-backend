package com.example.biblioteca.repository;

import com.example.biblioteca.model.entity.Copy;
import com.example.biblioteca.model.entity.Resource;
import com.example.biblioteca.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICopyRepository extends JpaRepository<Copy,Long> {
    List<Copy> findAllByResource(Resource resource);
    List<Transaction> findAllByTransactions (Copy copy);

}
