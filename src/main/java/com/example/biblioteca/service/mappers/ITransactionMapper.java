package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.TransactionDTO;
import com.example.biblioteca.model.dto.TransactionStatusDTO;
import com.example.biblioteca.model.entity.Transaction;
import com.example.biblioteca.model.entity.TransactionStatus;

import java.util.List;

public interface ITransactionMapper {
    Transaction dtoToEntity (TransactionDTO transactionDTO);
    TransactionDTO entityToDto (Transaction transaction);
    List<TransactionDTO> entityToDtoList (List<Transaction> transactions);
    List<Transaction> dtoListToEntity (List<TransactionDTO> transactionsDTO);
}
