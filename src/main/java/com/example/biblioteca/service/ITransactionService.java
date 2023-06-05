package com.example.biblioteca.service;

import com.example.biblioteca.model.entity.*;

import java.util.List;
import java.util.Optional;

public interface ITransactionService {
    List<Transaction> findAllTransactions ();
    Optional<Loan> saveLoan(Loan loan);

    List<Loan> findAllLoans();

    public Optional<Loan> findLoanById (Long id);

    Optional<Booking> saveBooking(Booking booking);
    Boolean delete(Long id);
    Transaction update (Transaction transaction);
    Optional<Transaction> findById (Long id);
    List<Transaction> findAllTransactionsByResource (Resource resource);

    List<Transaction> findAllTransactionsByUser (User user);


    Loan updateLoan (Loan loan);
    Optional<TransactionStatus> getTransactionStatusByType (String type);

    List<Transaction> getActiveLoansByUser(User user);

    List<Transaction> getCompletedLoansByUser(User user);

    List<Transaction> getActiveBookingsByUser(User user);

    Boolean deleteBooking(Long id);
}
