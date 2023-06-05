package com.example.biblioteca.repository;

import com.example.biblioteca.model.entity.Loan;
import com.example.biblioteca.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ILoanRepository extends JpaRepository<Loan,Long> {

    List<Loan> findAll ();
}
